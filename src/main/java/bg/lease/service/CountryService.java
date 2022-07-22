package bg.lease.service;

import bg.lease.model.CountryEntity;
import bg.lease.model.VendorEntity;
import bg.lease.model.dto.CountryDTO;
import bg.lease.model.dto.VendorDTO;
import bg.lease.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryDTO> smallListCountry(String searchKey){
        if (searchKey.equals("")){
            return this.countryRepository.findAll().stream().
                    map(this::mapSmall).collect(Collectors.toList());
        } else {
            return this.countryRepository.findByKeyword(searchKey).stream().
                    map(this::mapSmall).collect(Collectors.toList());
        }
    }

    private CountryDTO mapSmall(CountryEntity country) {
        CountryDTO result = new CountryDTO();
        result.setNo(country.getNo());
        result.setName(country.getName());

        return result;
    }

    public List<CountryDTO> listCountry(){
        return this.countryRepository.findAll().stream().
                map(this::map).collect(Collectors.toList());
    }

    private CountryDTO map(CountryEntity country) {
        CountryDTO result = new CountryDTO();
        result.setNo(country.getNo());
        result.setName(country.getName());

        return result;
    }

    public void addCard(CountryDTO countryDTO) {
        Optional<CountryEntity> byNo=this.countryRepository.findByNo(countryDTO.getNo());

        CountryEntity country;
        if (byNo.isPresent()){
            //throw  new RuntimeException("vendor no");
            country=byNo.get();
        } else {
            country = new CountryEntity();
        }
        country.setNo(countryDTO.getNo());
        country.setName(countryDTO.getName());

        countryRepository.save(country);
    }

    public CountryDTO editCard(String countryNo){
        Optional<CountryEntity> optCountry=countryRepository.findByNo(countryNo);
        if (optCountry.isEmpty()){
            throw new RuntimeException("country not found");
        }
        CountryEntity country=optCountry.get();
        return map(country);
    }
}
