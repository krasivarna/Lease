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
import java.math.MathContext;
import java.math.RoundingMode;
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

    private int getLastMonth(String contractNo, int lineNo, PayOffAmountType amountType, LocalDate dueDate){
        Optional<PayoffPlan> optEntry=payOffRepository.findFirstByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndDueDateLessThanOrderByDueDateDesc(
             contractNo,lineNo,false,amountType,dueDate);
        if (optEntry.isEmpty()){
            return 0;
        }
        int lastMonth=optEntry.get().getMonth();
        optEntry=payOffRepository.findFirstByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndMonthGreaterThanEqualAndInvoicedInterestOrderByMonthDesc(
                contractNo,lineNo,false,amountType,lastMonth,true);
        if (optEntry.isEmpty()){
            return lastMonth;
        }
        return optEntry.get().getMonth();
    }

    private int calcPayOffPlan(LeaseDetailDTO detail,LeaseHeaderEntity header,int type,boolean calculate,boolean reoffer){
        BigDecimal currentPrincipalExcl=BigDecimal.ZERO;
        BigDecimal currentPrincipalIncl=BigDecimal.ZERO;
        BigDecimal beginExcl=BigDecimal.ZERO;
        BigDecimal amountExcl=BigDecimal.ZERO;
        BigDecimal beginIncl=BigDecimal.ZERO;
        BigDecimal amountIncl=BigDecimal.ZERO;
        BigDecimal principalExcl=BigDecimal.ZERO;
        BigDecimal principalIncl=BigDecimal.ZERO;
        BigDecimal lastExcl=BigDecimal.ZERO;
        BigDecimal lastIncl=BigDecimal.ZERO;
        BigDecimal paymentExcl=BigDecimal.ZERO;
        BigDecimal paymentIncl=BigDecimal.ZERO;
        BigDecimal totalPayExcl=BigDecimal.ZERO;
        BigDecimal totalPayIncl=BigDecimal.ZERO;
        BigDecimal beforeAmountExcl=BigDecimal.ZERO;
        BigDecimal beforeAmountIncl=BigDecimal.ZERO;
        BigDecimal totalPrincipalExcl=BigDecimal.ZERO;
        BigDecimal totalPrincipalIncl=BigDecimal.ZERO;
        BigDecimal interestExcl=BigDecimal.ZERO;
        BigDecimal interestIncl=BigDecimal.ZERO;

        int currentMonth=0;
        int lastGracePeriod=0;
        int correctPeriod=0;
        int offsetMonth=0;
        int period=0;
        int lastPeriod=0;
        double koeficient=0.0;
        int maxMonth=0;
        long lastEntry=0;

        boolean setLastPayment=false;

        if (calculate){
            currentMonth=getLastMonth(detail.getContractNo(),detail.getLineNo(),PayOffAmountType.values()[type],header.getDateModifyPercent())+1;
            //recPayOff.SETRANGE("Contract No",pDetail."Contract No");
            //recPayOff.SETRANGE("Contract Line",pDetail."Line No");
            //recPayOff.SETFILTER(Month,'<%1',CurrentMonth);
            //recPayOff.SETRANGE("Amount Type",pType);
            //recPayOff.SETRANGE(bCancel,FALSE);
            //recPayOff.CALCSUMS("Principal Excl VAT","Principal Incl VAT");
            //currentPrincipalExcl:=recPayOff."Principal Excl VAT";
            //currentPrincipalIncl:=recPayOff."Principal Incl VAT";

            Optional<PayoffPlan> optEntry=payOffRepository.findFirstByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndMonthGreaterThanEqualAndEntryTypeOrderByMonthDesc(
                    detail.getContractNo(),detail.getLineNo(),false,PayOffAmountType.values()[type],currentMonth,PayOffEntryType.INTEREST);
            if (optEntry.isPresent()) {
                lastGracePeriod = optEntry.get().getMonth();
                lastGracePeriod = lastGracePeriod - currentMonth + 1;
                if (lastGracePeriod < 0) {
                    lastGracePeriod = 0;
                }
            }
        }

        offsetMonth=header.getNoFirstMonth()-1;

        switch (type){
            case 0:
            //decLastIncl:=pDetail."Last Payment Incl VAT";
            //decLastExcl:=pDetail."Last Payment Excl VAT";
                beginExcl=detail.getPrincipalExclVat().subtract(currentPrincipalExcl);
                amountExcl=beginExcl;

                beginIncl=detail.getPrincipalInclVat().subtract(currentPrincipalIncl);
                amountIncl=beginIncl;
                if( calculate) {
                    correctPeriod=currentMonth - header.getPrincipalGracePeriod() - 1 + lastGracePeriod - offsetMonth;
                    if (correctPeriod<0) {
                        correctPeriod=0;
                    }
                };
                period=header.getPrincipalPeriod()-header.getPrincipalGracePeriod()-correctPeriod-lastPeriod;
                koeficient=(header.getPrincipalInterest().doubleValue()/100)/12;
            break;
        }

        switch (header.getPaymentType()) {
            case EqPrincipal:
                if (period > 0) {
                    BigDecimal bPeriod=BigDecimal.valueOf(period);
                    principalExcl = amountExcl.subtract(lastExcl).divide(bPeriod,2,RoundingMode.HALF_DOWN);
                   principalIncl = amountIncl.subtract(lastIncl).divide(bPeriod,2,RoundingMode.HALF_DOWN);
                 }
                break;
            case EqPayment:
                if (period > 0) {
                    double tempPow=Math.pow(1+koeficient,period);
                    double tempMul=koeficient*tempPow;
                    paymentExcl=(amountExcl.multiply(BigDecimal.valueOf(tempMul)).
                            subtract(lastExcl.multiply(BigDecimal.valueOf(koeficient)))).divide(BigDecimal.valueOf(tempPow - 1));
                    paymentIncl=(amountIncl.multiply(BigDecimal.valueOf(tempMul)).
                            subtract(lastIncl.multiply(BigDecimal.valueOf(koeficient)))).divide(BigDecimal.valueOf(tempPow - 1));

                    switch (header.getLoanPaid()) {
                        case PaidBegin:
                            if (! calculate) {
                                BigDecimal divideKoeficient=BigDecimal.valueOf(1 + koeficient);
                                paymentExcl=paymentExcl.divide(divideKoeficient);
                                paymentIncl=paymentIncl.divide(divideKoeficient);
                            }
                            beforeAmountExcl=amountExcl;
                            beforeAmountIncl=amountIncl;
                            if (! calculate) {
                                amountExcl=BigDecimal.ZERO;
                                amountIncl=BigDecimal.ZERO;
                            }
                            break;

                        case PaidBegin_1:
                            if (!calculate) {
                                BigDecimal divideKoeficient=BigDecimal.valueOf(1 + koeficient);
                                paymentExcl=paymentExcl.divide(divideKoeficient);
                                paymentIncl=paymentIncl.divide(divideKoeficient);
                            }
                            break;
                    }

                    totalPayIncl=paymentIncl.multiply(BigDecimal.valueOf(period)).round(new MathContext(3));
                    totalPayExcl=paymentExcl.multiply(BigDecimal.valueOf(period)).round(new MathContext(3));
                    paymentExcl=paymentExcl.round(new MathContext(3));
                    paymentIncl=paymentIncl.round(new MathContext(3));

                }
                break;
        }

        Optional<PayoffPlan> optEntry=payOffRepository.findFirstByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndMonthGreaterThanEqualOrderByMonthDesc(
                detail.getContractNo(),detail.getLineNo(),false,PayOffAmountType.values()[type],currentMonth);
        if (optEntry.isPresent()){
            maxMonth=optEntry.get().getMonth();
        }

        List<PayoffPlan> details=payOffRepository.findByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancelAndAmountTypeAndMonthGreaterThanEqual(
                detail.getContractNo(),detail.getLineNo(),false,PayOffAmountType.values()[type],currentMonth);
        for(PayoffPlan detailUpdate:details){
            switch (header.getPaymentType()) {
                case EqPrincipal :
                    if (lastPeriod>0) {
                        if (detailUpdate.getMonth()==maxMonth) {
                            principalExcl = lastExcl;
                            principalIncl = lastIncl;
                            koeficient = 0;
                            setLastPayment = true;
                        }
                        if (detailUpdate.getMonth()==(maxMonth - lastPeriod)){
                            principalExcl=beginExcl.subtract(totalPrincipalExcl).subtract(lastExcl);
                            principalIncl=beginIncl.subtract(totalPrincipalIncl).subtract(lastIncl);
                        }
                    } else {
                        if (detailUpdate.getMonth() == maxMonth){
                            principalExcl=beginExcl.subtract(totalPrincipalExcl);
                            principalIncl=beginIncl.subtract(totalPrincipalIncl);
                        }
                    };

                    interestExcl=amountExcl.multiply(BigDecimal.valueOf(koeficient).round(new MathContext(3)));

                //IF bolIntWithVAT THEN
                //    IF recLeaseAmount.GET(pDetail."Contract No", pDetail."Vehicle Type") THEN
                //        decInterestIncl:=ROUND(decInterestExcl * (1 + recLeaseAmount."VAT %" / 100), recCurrency."Amount Rounding Precision");

                    if (detailUpdate.getEntryType()== PayOffEntryType.PRINCIPAL_INTEREST) {
                        totalPrincipalExcl= totalPrincipalExcl.add(principalExcl);
                        totalPrincipalIncl= totalPrincipalIncl.add(principalIncl);
                        detailUpdate.setPrincipalExclVAT(principalExcl);
                        //IF NOT bolVATPlan THEN
                        detailUpdate.setPrincipalInclVAT(principalIncl);
                        amountExcl= amountExcl.subtract(principalExcl);
                        amountIncl= amountIncl.subtract(principalIncl);
                    }
                    detailUpdate.setRemainingPrincipalExclVAT(amountExcl);
                    //IF NOT bolVATPlan THEN
                    detailUpdate.setRemainingPrincipalInclVAT(amountIncl);
                    detailUpdate.setInterestExclVAT(interestExcl);
                    detailUpdate.setInterestInclVAT(interestIncl);
                break;
            }
            detailUpdate.setLastPayment(setLastPayment);
            payOffRepository.save(detailUpdate);
            lastEntry=detailUpdate.getEntryNo();
        }
        return currentMonth;
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
