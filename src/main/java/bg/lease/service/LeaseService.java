package bg.lease.service;

import bg.lease.model.LeaseHeaderEntity;
import bg.lease.model.VendorEntity;
import bg.lease.model.dto.LeaseCardDTO;
import bg.lease.model.dto.LeaseDTO;
import bg.lease.model.dto.VendorSmallDTO;
import bg.lease.model.mapper.LeaseCardMapper;
import bg.lease.repository.LeaseRepository;
import bg.lease.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaseService {

    private LeaseRepository leaseRepository;
    private VendorRepository vendorRepository;
    private final LeaseCardMapper leaseCardMapper;

    public LeaseService(LeaseRepository leaseRepository,
                        VendorRepository vendorRepository,
                        LeaseCardMapper leaseCardMapper){
        this.leaseRepository=leaseRepository;
        this.vendorRepository=vendorRepository;
        this.leaseCardMapper=leaseCardMapper;
    }

    public void addCard(LeaseCardDTO leaseCardDTO) {
        Optional<LeaseHeaderEntity> byNo=this.leaseRepository.findByContractNo(leaseCardDTO.getContractNo());

        LeaseHeaderEntity leaseHeader;

        if (byNo.isPresent()){
        //    //throw  new RuntimeException("contract no");
            leaseHeader=byNo.get();
        } else {
            leaseHeader=new LeaseHeaderEntity();
        }
        Optional<VendorEntity> optVendor=vendorRepository.findByNo(leaseCardDTO.getVendorNo());
        if (optVendor.isEmpty()){
            throw  new RuntimeException("vendor not exit");
        }
        leaseCardMapper.leaseCardDtoLeaseHeaderEntity(leaseCardDTO,leaseHeader);
        leaseHeader.setVendorNo(optVendor.get());
        leaseRepository.save(leaseHeader);
    }

    public List<LeaseDTO> listLease(){
        return this.leaseRepository.findAll().stream().
                map(this::map).collect(Collectors.toList());
    }

    private LeaseDTO map(LeaseHeaderEntity lease){
        LeaseDTO result=new LeaseDTO();
        result.setContractNo(lease.getContractNo());
        result.setContractDate(lease.getContractDate());

        VendorSmallDTO vendor=new VendorSmallDTO();
        vendor.setNo(lease.getVendorNo().getNo());
        vendor.setName(lease.getVendorNo().getName());
        vendor.setVatRegistration(lease.getVendorNo().getVatRegistration());
        result.setVendor(vendor);
        return result;
    }

    public LeaseCardDTO editCard(String contractNo){
        Optional<LeaseHeaderEntity> optContract=leaseRepository.findByContractNo(contractNo);
        if (optContract.isEmpty()){
            throw new RuntimeException("contract not found");
        }
        LeaseHeaderEntity header=optContract.get();
        LeaseCardDTO result=leaseCardMapper.leaseHeaderEntityToLeaseCardDto(header);
        result.setVendorNo(header.getVendorNo().getNo());

        return result;
    }
}
