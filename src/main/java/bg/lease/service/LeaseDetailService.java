package bg.lease.service;

import bg.lease.model.LeaseDetailEntity;
import bg.lease.model.LeaseHeaderEntity;
import bg.lease.model.dto.LeaseDTO;
import bg.lease.model.dto.LeaseDetailDTO;
import bg.lease.model.dto.VendorSmallDTO;
import bg.lease.repository.LeaseDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseDetailService {

    private LeaseDetailRepository leaseDetailRepository;

    public LeaseDetailService(LeaseDetailRepository leaseDetailRepository) {

        this.leaseDetailRepository = leaseDetailRepository;
    }

    public List<LeaseDetailDTO> leaseDetail(String contractNo){
        return this.leaseDetailRepository.findByContractNo(contractNo).stream().
                map(this::map).collect(Collectors.toList());
    }

    private LeaseDetailDTO map(LeaseDetailEntity detail){
        LeaseDetailDTO result=new LeaseDetailDTO();
        result.setContractNo(detail.getContractNo());
        result.setLineNo(detail.getLineNo());
        result.setVehicleNo(detail.getVehicle().getNo());
        result.setNumberPlate(detail.getVehicle().getNumberPlate());

        return result;
    }
}
