package bg.lease.service;

import bg.lease.model.CountryEntity;
import bg.lease.model.VehicleEntity;
import bg.lease.model.VendorEntity;
import bg.lease.model.dto.CountryDTO;
import bg.lease.model.dto.VehicleDTO;
import bg.lease.model.dto.VendorDTO;
import bg.lease.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleDTO> listVehicle() {
        return this.vehicleRepository.findAll().stream().
                map(this::map).collect(Collectors.toList());
    }

    private VehicleDTO map(VehicleEntity vehicle) {
        VehicleDTO result=new VehicleDTO();
        result.setNo(vehicle.getNo());
        result.setNumberPlate(vehicle.getNumberPlate());
        result.setVehicleModel(vehicle.getVehicleModel());

        return result;
    }

    public void addCard(VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> byNo=this.vehicleRepository.findByNo(vehicleDTO.getNo());

        VehicleEntity vehicle;
        if (byNo.isPresent()){
            //throw  new RuntimeException("vendor no");
            vehicle=byNo.get();
        } else {
            vehicle = new VehicleEntity();
        }
        vehicle.setNo(vehicleDTO.getNo());
        vehicle.setNumberPlate(vehicleDTO.getNumberPlate());
        vehicle.setVehicleModel(vehicleDTO.getVehicleModel());

        vehicleRepository.save(vehicle);
    }

    public VehicleDTO editCard(String vehicleNo){
        Optional<VehicleEntity> optVehicle=vehicleRepository.findByNo(vehicleNo);
        if (optVehicle.isEmpty()){
            throw new RuntimeException("vehicle not found");
        }
        VehicleEntity vehicle=optVehicle.get();
        return map(vehicle);
    }
}
