package bg.lease.util;

import bg.lease.model.*;
import bg.lease.model.enums.PermissionType;
import bg.lease.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitDatabase implements CommandLineRunner {

    private CountryRepository countryRepository;
    private VehicleRepository vehicleRepository;
    private VendorRepository vendorRepository;
    private PermissionRepository permissionRepository;
    private UserPermissionRepository userPermissionRepository;
    private UserRepository userRepository;

    public InitDatabase(CountryRepository countryRepository,
                        VehicleRepository vehicleRepository,
                        VendorRepository vendorRepository,
                        PermissionRepository permissionRepository,
                        UserPermissionRepository userPermissionRepository,
                        UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.vehicleRepository = vehicleRepository;
        this.vendorRepository = vendorRepository;
        this.permissionRepository = permissionRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        setCountries();
        setVehicles();
        setVendors();
        setPermissions();
        setUserPermission();
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

    private void setPermissions(){
        if (this.permissionRepository.count()==0){
            List<PermissionEntity> result=new ArrayList<>();
            result.add(new PermissionEntity("country", PermissionType.COUNTRY));
            result.add(new PermissionEntity("vehicle",PermissionType.VEHICLE));
            result.add(new PermissionEntity("vendor",PermissionType.VENDOR));
            result.add(new PermissionEntity("lease",PermissionType.LEASE));
            result.add(new PermissionEntity("lease detail",PermissionType.LEASE_DETAIL));
            result.add(new PermissionEntity("payoff",PermissionType.PAYOFF));
            result.add(new PermissionEntity("invoice payment",PermissionType.INVOICE_PAYMENT));
            result.add(new PermissionEntity("user permissions",PermissionType.USER_PERMISSION));

            permissionRepository.saveAll(result);
        }
    }

    private void setUserPermission(){
        if ((this.userPermissionRepository.count()==0) && (this.userRepository.count()==1)) {
            List<UserPermissionEntity> result=new ArrayList<>();
            result.add(new UserPermissionEntity(userRepository.findById(Long.valueOf(1)).get(),
                    permissionRepository.findByPermissionType(PermissionType.COUNTRY).get(),
                                        true,true,true,true));
            result.add(new UserPermissionEntity(userRepository.findById(Long.valueOf(1)).get(),
                    permissionRepository.findByPermissionType(PermissionType.VEHICLE).get(),
                    true,true,true,true));
            result.add(new UserPermissionEntity(userRepository.findById(Long.valueOf(1)).get(),
                    permissionRepository.findByPermissionType(PermissionType.VENDOR).get(),
                    true,true,true,true));
            result.add(new UserPermissionEntity(userRepository.findById(Long.valueOf(1)).get(),
                    permissionRepository.findByPermissionType(PermissionType.LEASE).get(),
                    true,true,true,true));
            result.add(new UserPermissionEntity(userRepository.findById(Long.valueOf(1)).get(),
                    permissionRepository.findByPermissionType(PermissionType.LEASE_DETAIL).get(),
                    true,true,true,true));
            result.add(new UserPermissionEntity(userRepository.findById(Long.valueOf(1)).get(),
                    permissionRepository.findByPermissionType(PermissionType.PAYOFF).get(),
                    true,true,true,true));
            result.add(new UserPermissionEntity(userRepository.findById(Long.valueOf(1)).get(),
                    permissionRepository.findByPermissionType(PermissionType.INVOICE_PAYMENT).get(),
                    true,true,true,true));
            result.add(new UserPermissionEntity(userRepository.findById(Long.valueOf(1)).get(),
                    permissionRepository.findByPermissionType(PermissionType.USER_PERMISSION).get(),
                    true,true,true,true));

            userPermissionRepository.saveAll(result);
        }
    }
}
