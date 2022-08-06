package bg.lease.util;

import bg.lease.model.CountryEntity;
import bg.lease.model.VehicleEntity;
import bg.lease.model.VendorEntity;
import bg.lease.repository.CountryRepository;
import bg.lease.repository.VehicleRepository;
import bg.lease.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitDatabase implements CommandLineRunner {

    private CountryRepository countryRepository;
    private VehicleRepository vehicleRepository;
    private VendorRepository vendorRepository;

    public InitDatabase(CountryRepository countryRepository,
                        VehicleRepository vehicleRepository,
                        VendorRepository vendorRepository) {
        this.countryRepository = countryRepository;
        this.vehicleRepository = vehicleRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        setCountries();
        setVehicles();
        setVendors();
    }

    private void setCountries(){
        if (this.countryRepository.count()==0){
            List<CountryEntity> results=new ArrayList<>();
            results.add(new CountryEntity("BG","България"));
            results.add(new CountryEntity("RO","Румъния"));
            results.add(new CountryEntity("DE","Германия"));
            results.add(new CountryEntity("GR","Гърция"));
            results.add(new CountryEntity("FR","Франция"));

            countryRepository.saveAll(results);
        }
    }

    private void setVehicles(){
        if (this.vehicleRepository.count()==0){
            List<VehicleEntity> results=new ArrayList<>();
            results.add(new VehicleEntity("001","B 1010 KK","BMV X5"));
            results.add(new VehicleEntity("002","CA 6723 HP","Шкода Октавия"));
            results.add(new VehicleEntity("003","K 7421 CC","Фолксвафен Голф 5"));
            results.add(new VehicleEntity("004","PP 5013 KP","Мазда 6"));
            results.add(new VehicleEntity("005","EB 8321 BK","Форд Ескорт РС"));
            results.add(new VehicleEntity("006","B 6500 MH","Ситруен"));
            results.add(new VehicleEntity("007","TX 7712 YC","Хонда Сивик"));

            vehicleRepository.saveAll(results);
        }
    }

    private void setVendors(){
        if (vendorRepository.count()==0){
            List<VendorEntity> results=new ArrayList<>();
            results.add(new VendorEntity("Д-0001","Турбо ООД","BG111111111",
                    "ул. Петко Стайнов 3",countryRepository.findByNo("BG").get(),"Варна"));
            results.add(new VendorEntity("Д-0002","Мая ООД","BG111551111",
                    "бул. Сливница 43",countryRepository.findByNo("BG").get(),"София"));
            results.add(new VendorEntity("Д-0003","Venera Ltd","DE451764561",
                    "Strase Garden 3",countryRepository.findByNo("DE").get(),"Hamburg"));
            results.add(new VendorEntity("Д-0004","Петко транс","BG143461111",
                    "ул. 8 Септември 66",countryRepository.findByNo("BG").get(),"Русе"));

            vendorRepository.saveAll(results);
        }
    }
}
