package bg.lease.service;

import bg.lease.model.LeaseHeaderEntity;
import bg.lease.model.PayoffPlan;
import bg.lease.model.dto.LeaseDetailDTO;
import bg.lease.model.enums.*;
import bg.lease.repository.LeaseRepository;
import bg.lease.repository.PayOffRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PayOffGenerateService {

    private LeaseRepository leaseRepository;
    private LeaseDetailService leaseDetailService;
    private PayOffRepository payOffRepository;

    public PayOffGenerateService(LeaseRepository leaseRepository,
                                 LeaseDetailService leaseDetailService,
                                 PayOffRepository payOffRepository) {
        this.leaseRepository = leaseRepository;
        this.leaseDetailService = leaseDetailService;
        this.payOffRepository = payOffRepository;
    }

    public void generatePayoffPlan(String contractNo) {
        Optional<LeaseHeaderEntity> header=leaseRepository.findByContractNo(contractNo);
        if (header.isEmpty()){
            throw new RuntimeException("Contract not found");
        }
        generatePayoffPlan(header.get(),TypePlan.NEW_PLAN,0,false);
        calcDetailPrincipal(header.get());
    }


    private boolean generatePayoffPlan(LeaseHeaderEntity leaseHeader,
                                      TypePlan typePlan, int newMonth,
                                      boolean bollAuto){

        if (leaseHeader.getPrincipalPeriod()==0){
            throw new RuntimeException("Missing principal period");
        }
        if (typePlan==TypePlan.UPDATE_DUE_DATE){
            if (leaseHeader.getBeginPayOffDate()==null){
              throw new RuntimeException("Missing begin payoff date");
            }
            //IF NOT pbAuto THEN
            //IF GetStatusAction(recLeaseHdr."Contract No",0,0)=0 THEN
            //ERROR(Text002);
        } else {
            //IF NOT pbAuto THEN
            //IF GetStatusAction(recLeaseHdr."Contract No",0,0)=0 THEN
            //ERROR(Text001);
        }

        TypeEntries typeEntries;
        if (leaseHeader.getInvoiceType()== InvoiceType.InBegin) {
            if (leaseHeader.getPrincipalExclVAT().signum() == 0) {
                throw new RuntimeException("Missing principal excl vat");
            }
            if (leaseHeader.getPrincipalInclVAT().signum() == 0) {
                throw new RuntimeException("Missing principal incl vat");
            }
            //IF recLeaseHdr."Principal Incl VAT"-recLeaseHdr."Principal Excl VAT">0 THEN BEGIN
            //    recLeaseHdr.TESTFIELD("VAT Period");
            //    iType:=1;
            //END ELSE
        }
        typeEntries = TypeEntries.Plan_For_Principal;

        int offsetMonth= leaseHeader.getNoFirstMonth();

        List<LeaseDetailDTO> details=leaseDetailService.leaseDetail(leaseHeader.getContractNo());
        for(LeaseDetailDTO detail : details){
            int startMonth;
            if (newMonth==0){
                startMonth=offsetMonth;
            } else {
                startMonth=newMonth;
            }

            for(int forType=TypeEntries.Plan_For_Principal.ordinal();forType<=typeEntries.ordinal();forType++ ){
                int lastGracePeriod=0;
                int periods=0;
                int gracePeriods=0;

                switch (forType) {
                    case 0 :
                        periods= leaseHeader.getPrincipalPeriod();
                        gracePeriods= leaseHeader.getPrincipalGracePeriod();
                        break;
                }
                if ((newMonth!=0) && (gracePeriods!=0)) {
                    lastGracePeriod=payOffRepository.countByLeaseDetail_ContractNoAndEntryTypeAndLeaseDetail_LineNoAndCancelAndAmountType(
                            detail.getContractNo(), PayOffEntryType.INTEREST,detail.getLineNo(),false,PayOffAmountType.values()[forType]);
                }
                int countGrace=lastGracePeriod;
                PayOffEntryType typeEntry;
                periods=periods+offsetMonth-1;

                for(int currentMonth=startMonth;currentMonth<=periods;currentMonth++){
                    PayoffPlan payPlan;
                    Optional<PayoffPlan> optPayplan=payOffRepository.findByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndMonthAndAmountTypeAndCancel(
                            detail.getContractNo(),detail.getLineNo(),currentMonth,PayOffAmountType.values()[forType],false);
                    if (optPayplan.isEmpty()){
                        if ((gracePeriods>0) && (countGrace<gracePeriods)) {
                            countGrace += 1;
                            typeEntry = PayOffEntryType.INTEREST;
                        }else {
                            typeEntry=PayOffEntryType.PRINCIPAL_INTEREST;
                        }
                        payPlan=new PayoffPlan();
                        payPlan.createPayoffPlan(currentMonth,PayOffAmountType.values()[forType],typeEntry);
                        payPlan.setLeaseDetail(leaseDetailService.getLeaseDetail(detail.getContractNo(), detail.getLineNo()));
                        LocalDate dueDate=leaseHeader.getBeginPayOffDate();
                        payPlan.setDueDate(dueDate.plusMonths(currentMonth-offsetMonth));

                        payOffRepository.save(payPlan);
                    } else {
                        switch (typePlan) {
                            case UPDATE_DUE_DATE:
                                payPlan=optPayplan.get();
                                LocalDate dueDate=leaseHeader.getBeginPayOffDate();
                                payPlan.setDueDate(dueDate.plusMonths(currentMonth-offsetMonth));
                                payOffRepository.save(payPlan);
                                break;
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean checkLeaseRules(LeaseHeaderEntity leaseHeader){
        if (leaseHeader.getPrincipalExclVAT().signum()==0){
            throw new RuntimeException("Principal Excl VAT is zero");
        }
        if (leaseHeader.getPrincipalInclVAT().signum()==0){
            throw new RuntimeException("Principal Incl VAT is zero");
        }
        if (leaseHeader.getPrincipalInterest().signum()==0){
            throw new RuntimeException("Principal Interest percent is zero");
        }
        if (leaseHeader.getPrincipalPeriod()==0){
            throw new RuntimeException("Principal Period is zero");
        }

        BigDecimal leaseTotalIncl=leaseHeader.getPrincipalInclVAT().subtract(leaseHeader.getBeginPaymentInclVAT());
        BigDecimal leaseTotalExcl=leaseHeader.getPrincipalExclVAT().subtract(leaseHeader.getBeginPaymentExclVAT());
        BigDecimal calculateTotalIncl=leaseDetailService.totalInclAmount(leaseHeader.getContractNo());
        BigDecimal calculateTotalExcl=leaseDetailService.totalExclAmount(leaseHeader.getContractNo());

        if (leaseTotalIncl.compareTo(calculateTotalIncl)!=0){
            throw new RuntimeException("Amount Incl VAT is not equal with calculated amount");
        }

        if (leaseTotalExcl.compareTo(calculateTotalExcl)!=0){
            throw new RuntimeException("Amount Excl VAT is not equal with calculated amount");
        }

        return true;
    }

    private boolean calcDetailPrincipal(LeaseHeaderEntity leaseHeader){
        if (!checkLeaseRules(leaseHeader)) {
            return false;
        }
        List<LeaseDetailDTO> details=leaseDetailService.leaseDetail(leaseHeader.getContractNo());
        for (LeaseDetailDTO detail:details){
            calcPayOffPlan(detail,leaseHeader,0,false,false);
        }
        return true;
    }

    private int calcPayOffPlan(LeaseDetailDTO detail,LeaseHeaderEntity header,int type,boolean calculate,boolean reoffer){

        return 0;
    }

    @Transactional
    public boolean deletePayoffPlan(String contractNo) {
        if (payOffRepository.existsByLeaseDetail_ContractNoAndInvoicedPrincipalAndCancel(contractNo,true,false)){
            throw new RuntimeException("Some payments are invoiced. The Action is canceled.");
        }
        if (payOffRepository.existsByLeaseDetail_ContractNoAndInvoicedInterestAndCancel(contractNo,true,false)){
            throw new RuntimeException("Some payments are invoiced. The Action is canceled.");
        }
        //recapply test
        payOffRepository.deleteByLeaseDetail_ContractNo(contractNo);
        return true;
    }
}
