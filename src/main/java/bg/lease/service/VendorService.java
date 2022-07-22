package bg.lease.service;

import bg.lease.model.CountryEntity;
import bg.lease.model.VendorEntity;
import bg.lease.model.dto.VendorDTO;
import bg.lease.model.dto.VendorSmallDTO;
import bg.lease.repository.CountryRepository;
import bg.lease.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorService {
    private VendorRepository vendorRepository;
    private CountryRepository countryRepository;

    public VendorService(VendorRepository vendorRepository,
                         CountryRepository countryRepository){
        this.vendorRepository=vendorRepository;
        this.countryRepository = countryRepository;
    }

    public void addCard(VendorDTO vendorDTO) {
        Optional<VendorEntity> byNo=this.vendorRepository.findByNo(vendorDTO.getNo());

        Optional<CountryEntity> byCountryNo=this.countryRepository.findByNo(vendorDTO.getCountry());
        if (byCountryNo.isEmpty()){
            throw new RuntimeException("country no is not exist");
        }
        VendorEntity vendor;
        if (byNo.isPresent()){
            //throw  new RuntimeException("vendor no");
            vendor=byNo.get();
        } else {
            vendor = new VendorEntity();
        }
        vendor.setNo(vendorDTO.getNo());
        vendor.setName(vendorDTO.getName());
        vendor.setVatRegistration(vendorDTO.getVatRegistration());
        vendor.setCity(vendorDTO.getCity());
        vendor.setAddress(vendorDTO.getAddress());
        vendor.setCountryNo(byCountryNo.get());

        vendorRepository.save(vendor);
    }

    public List<VendorDTO> listVendor(){
        return this.vendorRepository.findAll().stream().
                map(this::map).collect(Collectors.toList());
    }

    private VendorDTO map(VendorEntity vendor){

        VendorDTO result=new VendorDTO();
        result.setNo(vendor.getNo());
        result.setName(vendor.getName());
        result.setVatRegistration(vendor.getVatRegistration());
        result.setCity(vendor.getCity());
        result.setAddress(vendor.getAddress());
        if (vendor.getCountryNo()==null){
            result.setCountry("");
        } else {
            result.setCountry(vendor.getCountryNo().getNo());
        }
        return result;
    }

    public List<VendorSmallDTO> smallListVendor(String searchKey){
        if (searchKey.equals("")){
            return this.vendorRepository.findAll().stream().
                    map(this::mapSmall).collect(Collectors.toList());
        } else {
            return this.vendorRepository.findByKeyword(searchKey).stream().
                    map(this::mapSmall).collect(Collectors.toList());
        }
    }

    private VendorSmallDTO mapSmall(VendorEntity vendor) {
        VendorSmallDTO result = new VendorSmallDTO();
        result.setNo(vendor.getNo());
        result.setName(vendor.getName());
        result.setVatRegistration(vendor.getVatRegistration());

        return result;
    }

    public VendorDTO editCard(String vendorNo){
        Optional<VendorEntity> optVendor=vendorRepository.findByNo(vendorNo);
        if (optVendor.isEmpty()){
            throw new RuntimeException("vendor not found");
        }
        VendorEntity vendor=optVendor.get();
        return map(vendor);
    }
}
