package bg.lease.service;

import bg.lease.model.LeaseDetailEntity;
import bg.lease.model.LeaseHeaderEntity;
import bg.lease.model.VehicleEntity;
import bg.lease.model.VendorEntity;
import bg.lease.model.dto.LeaseDetailDTO;
import bg.lease.repository.LeaseDetailRepository;
import bg.lease.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaseDetailService {

    private LeaseDetailRepository leaseDetailRepository;
    private VehicleRepository vehicleRepository;

    public LeaseDetailService(LeaseDetailRepository leaseDetailRepository,
                              VehicleRepository vehicleRepository) {
        this.leaseDetailRepository = leaseDetailRepository;
        this.vehicleRepository = vehicleRepository;
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

    public LeaseDetailDTO editLeaseDetailCard(String contractNo,int lineNo){
        LeaseDetailDTO detail;

        Optional<LeaseDetailEntity> optDetail = this.leaseDetailRepository.findByContractNoAndLineNo(contractNo, lineNo);

        if (optDetail.isPresent()){
             detail=map(optDetail.get());
         } else {
             detail=new LeaseDetailDTO();
             detail.setContractNo(contractNo);
         }
        return detail;
    }

    public void addDetailCard(LeaseDetailDTO leaseDetailDTO) {
        Optional<LeaseDetailEntity> byNo=this.leaseDetailRepository.findByContractNoAndLineNo(leaseDetailDTO.getContractNo(),
                                                leaseDetailDTO.getLineNo());

        LeaseDetailEntity leaseDetail;

        if (byNo.isPresent()){
            //    //throw  new RuntimeException("contract no");
            leaseDetail=byNo.get();
        } else {
            leaseDetail=new LeaseDetailEntity();
            leaseDetail.setContractNo(leaseDetailDTO.getContractNo());
            Optional<LeaseDetailEntity> optMaxLineNo=this.leaseDetailRepository.findLastLineNo(leaseDetailDTO.getContractNo());
            int lineNo=0;
            if (optMaxLineNo.isEmpty()) {
                lineNo=1000;
            } else {
                lineNo=optMaxLineNo.get().getLineNo()+1000;
            }
            leaseDetail.setLineNo(lineNo);
        }
        Optional<VehicleEntity> optVehicle=vehicleRepository.findByNo(leaseDetailDTO.getVehicleNo());
        if (optVehicle.isEmpty()){
            throw  new RuntimeException("vehicle not exit");
        }

        leaseDetail.setVehicle(optVehicle.get());
        leaseDetailRepository.save(leaseDetail);
    }

    @Transactional
    public void deleteCard(String contractNo, int lineNo) {
        leaseDetailRepository.deleteByContractNoAndLineNo(contractNo,lineNo);
    }
}
