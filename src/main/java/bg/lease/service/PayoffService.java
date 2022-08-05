package bg.lease.service;

import bg.lease.model.PayoffPlan;
import bg.lease.model.dto.PaySmallDTO;
import bg.lease.model.dto.PayoffListDTO;
import bg.lease.repository.PayOffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayoffService {

    private PayOffRepository payOffRepository;

    public PayoffService(PayOffRepository payOffRepository) {
        this.payOffRepository = payOffRepository;
    }

    public List<PayoffListDTO> payoffList(String contractNo, int lineNo) {
        return payOffRepository.findByLeaseDetail_ContractNoAndLeaseDetail_LineNoAndCancel(contractNo,lineNo,false)
        .stream()
                .map(this::map).collect(Collectors.toList());
    }

    private PayoffListDTO map(PayoffPlan payplan){
        PayoffListDTO result=new PayoffListDTO();
        result.setEntryNo(payplan.getEntryNo());
        result.setDetail(payplan.getLeaseDetail());
        result.setDueDate(payplan.getDueDate());
        result.setMonth(payplan.getMonth());
        result.setPrincipalExclVAT(payplan.getPrincipalExclVAT());
        result.setInterestExclVAT(payplan.getInterestExclVAT());

        return result;
    }

    public List<PaySmallDTO> smallListPay(String searchKey){
        if (searchKey.equals("")){
            return this.payOffRepository.findAll().stream().
                    map(this::mapSmall).collect(Collectors.toList());
        } else {
            return this.payOffRepository.findByLeaseDetail_ContractNo(searchKey).stream().
                    map(this::mapSmall).collect(Collectors.toList());
        }
    }

    private PaySmallDTO mapSmall(PayoffPlan entity) {
        PaySmallDTO result = new PaySmallDTO();
        result.setEntryNo(entity.getEntryNo());
        result.setMonth(entity.getMonth());
        result.setContractNo(entity.getLeaseDetail().getContractNo());
        result.setLineNo(entity.getLeaseDetail().getLineNo());

        return result;
    }
}
