package bg.lease.service;

import bg.lease.model.LeaseDetailEntity;
import bg.lease.model.LeaseHeaderEntity;
import bg.lease.model.PayoffPlan;
import bg.lease.model.dto.LeaseDetailDTO;
import bg.lease.model.enums.*;
import bg.lease.repository.LeaseDetailRepository;
import bg.lease.repository.LeaseRepository;
import bg.lease.repository.PayOffRepository;
import org.springframework.stereotype.Service;

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
        if (leaseHeader.getInvoiceType()== InvoiceType.InBegin){
            if (leaseHeader.getPrincipalExclVAT().signum()==0){
                throw new RuntimeException("Missing principal excl vat");
            }
            if (leaseHeader.getPrincipalInclVAT().signum()==0){
                throw new RuntimeException("Missing principal incl vat");
            }
            //IF recLeaseHdr."Principal Incl VAT"-recLeaseHdr."Principal Excl VAT">0 THEN BEGIN
            //    recLeaseHdr.TESTFIELD("VAT Period");
            //    iType:=1;
            //END ELSE
            typeEntries = TypeEntries.Plan_For_Principal;
        } else {
            typeEntries = TypeEntries.Plan_For_Principal;
        }

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
            //  jj:=j;
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
    //    recDetail.RESET;
    //    recDetail.SETRANGE("Contract No",recLeaseHdr."Contract No");
    //    IF recDetail.FIND('-') THEN
    //            REPEAT
    //                IF piNewMonth=0 THEN
    //                    iStart:=iOffsetMonth
    //                ELSE
    //                    iStart:=piNewMonth;
    //                FOR j:=0 TO iType DO BEGIN

    //                    iLastGPeriod:=0;

    //                    IF recDetail."Vehicle Type"=recDetail."Vehicle Type"::Service THEN BEGIN
    //                        IF j=0 THEN
    //                            jj:=3
    //                        ELSE
    //                            jj:=4;
    //                    END ELSE
    //                        jj:=j;

    //                    CASE j OF
    //                        0: BEGIN
    //                            iPeriod:=recLeaseHdr."Principal Period";
    //                            iGPeriod:=recLeaseHdr."Principal Grace Period"
    //                        END;
    //                        1: BEGIN
    //                            iPeriod:=recLeaseHdr."VAT Period";
    //                            iGPeriod:=recLeaseHdr."VAT Grace Period"
    //                        END;
    //                    END;

    //                    IF (piNewMonth<>0) AND (iGPeriod<>0) THEN BEGIN
    //                        recPay.RESET;
    //                        recPay.SETCURRENTKEY(bCancel,"Contract No","Contract Line",Month,"Amount Type");
    //                        recPay.SETRANGE("Contract No",recDetail."Contract No");
    //                        recPay.SETRANGE("Contract Line",recDetail."Line No");
    //                        recPay.SETRANGE(bCancel,FALSE);
    //                        recPay.SETRANGE("Amount Type",jj);
    //                        recPay.SETRANGE("Entry Type",recPay."Entry Type"::Interest);
    //                        iLastGPeriod:=recPay.COUNT;
    //                    END;

    //                    iCountGrace:=iLastGPeriod;
    //                    iPeriod:=iPeriod+iOffsetMonth-1;

    //                    FOR i:=iStart TO iPeriod DO BEGIN
    //                        recPayOff.IfExists(recDetail."Contract No",
    //                                            recDetail."Line No",
    //                                            i,jj);
    //                        IF NOT recPayOff.FINDFIRST THEN BEGIN
    //                            recPayOff."Contract No":=recDetail."Contract No";
    //                            recPayOff."Vendor No":=recLeaseHdr."Vendor No";
    //                            recPayOff."FA No":=recDetail."FA No";
    //                            recPayOff."Contract Line":=recDetail."Line No";
    //                            recPayOff.Month:=i;
    //                            IF (iGPeriod>0) AND (iCountGrace<iGPeriod) THEN BEGIN
    //                                iCountGrace+=1;
    //                                recPayOff."Entry Type":=recPayOff."Entry Type"::Interest
    //                            END ELSE
    //                                recPayOff."Entry Type":=recPayOff."Entry Type"::Both;
    //                            recPayOff."Amount Type":=jj;
    //                            recPayOff.INSERT(TRUE);
    //                        END;
    //                        CASE popType OF
    //                            1 : BEGIN
    //                                recPayOff."Due date":=CALCDATE('<+'+FORMAT(i-iOffsetMonth)+'M>',recLeaseHdr."Begin PayOff Date");
    //                                recPayOff.MODIFY;
    //                            END;
    //                        END;
    //                    END;
    //                END;
    //            UNTIL recDetail.NEXT=0;

        return true;
    }
}
