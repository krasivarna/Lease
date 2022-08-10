package bg.lease.service;

import bg.lease.model.UserPermissionEntity;
import bg.lease.model.enums.ActionType;
import bg.lease.model.enums.PermissionType;
import bg.lease.repository.UserPermissionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GlobalPermissionService {

    private UserPermissionRepository userPermissionRepository;

    public GlobalPermissionService(UserPermissionRepository userPermissionRepository) {
        this.userPermissionRepository = userPermissionRepository;
    }

    public boolean CountryIsRead(String username){
        return getPermissionFromRepository(username,PermissionType.COUNTRY,ActionType.READ);
    }
    public boolean CountryIsInsert(String username){
        return getPermissionFromRepository(username,PermissionType.COUNTRY,ActionType.INSERT);
    }
    public boolean CountryIsUpdate(String username){
        return getPermissionFromRepository(username,PermissionType.COUNTRY,ActionType.UPDATE);
    }
    public boolean CountryIsDelete(String username){
        return getPermissionFromRepository(username,PermissionType.COUNTRY,ActionType.DELETE);
    }

    public boolean VehicleIsRead(String username){
        return getPermissionFromRepository(username,PermissionType.VEHICLE,ActionType.READ);
    }
    public boolean VehicleIsInsert(String username){
        return getPermissionFromRepository(username,PermissionType.VEHICLE,ActionType.INSERT);
    }
    public boolean VehicleIsUpdate(String username){
        return getPermissionFromRepository(username,PermissionType.VEHICLE,ActionType.UPDATE);
    }
    public boolean VehicleIsDelete(String username){
        return getPermissionFromRepository(username,PermissionType.VEHICLE,ActionType.DELETE);
    }

    public boolean VendorIsRead(String username){
        return getPermissionFromRepository(username,PermissionType.VENDOR,ActionType.READ);
    }
    public boolean VendorIsInsert(String username){
        return getPermissionFromRepository(username,PermissionType.VENDOR,ActionType.INSERT);
    }
    public boolean VendorIsUpdate(String username){
        return getPermissionFromRepository(username,PermissionType.VENDOR,ActionType.UPDATE);
    }
    public boolean VendorIsDelete(String username){
        return getPermissionFromRepository(username,PermissionType.VENDOR,ActionType.DELETE);
    }

    public boolean LeaseIsRead(String username){
        return getPermissionFromRepository(username,PermissionType.LEASE,ActionType.READ);
    }
    public boolean LeaseIsInsert(String username){
        return getPermissionFromRepository(username,PermissionType.LEASE,ActionType.INSERT);
    }
    public boolean LeaseIsUpdate(String username){
        return getPermissionFromRepository(username,PermissionType.LEASE,ActionType.UPDATE);
    }
    public boolean LeaseIsDelete(String username){
        return getPermissionFromRepository(username,PermissionType.LEASE,ActionType.DELETE);
    }

    public boolean LeaseDetailCountryIsRead(String username){
        return getPermissionFromRepository(username,PermissionType.LEASE_DETAIL,ActionType.READ);
    }
    public boolean LeaseDetailIsInsert(String username){
        return getPermissionFromRepository(username,PermissionType.LEASE_DETAIL,ActionType.INSERT);
    }
    public boolean LeaseDetailIsUpdate(String username){
        return getPermissionFromRepository(username,PermissionType.LEASE_DETAIL,ActionType.UPDATE);
    }
    public boolean LeaseDetailIsDelete(String username){
        return getPermissionFromRepository(username,PermissionType.LEASE_DETAIL,ActionType.DELETE);
    }

    public boolean PayoffIsRead(String username){
        return getPermissionFromRepository(username,PermissionType.PAYOFF,ActionType.READ);
    }
    public boolean PayoffIsInsert(String username){
        return getPermissionFromRepository(username,PermissionType.PAYOFF,ActionType.INSERT);
    }
    public boolean PayoffIsUpdate(String username){
        return getPermissionFromRepository(username,PermissionType.PAYOFF,ActionType.UPDATE);
    }
    public boolean PayoffIsDelete(String username){
        return getPermissionFromRepository(username,PermissionType.PAYOFF,ActionType.DELETE);
    }

    public boolean InvPayIsRead(String username){
        return getPermissionFromRepository(username,PermissionType.INVOICE_PAYMENT,ActionType.READ);
    }
    public boolean InvPayIsInsert(String username){
        return getPermissionFromRepository(username,PermissionType.INVOICE_PAYMENT,ActionType.INSERT);
    }
    public boolean InvPayIsUpdate(String username){
        return getPermissionFromRepository(username,PermissionType.INVOICE_PAYMENT,ActionType.UPDATE);
    }
    public boolean InvPayIsDelete(String username){
        return getPermissionFromRepository(username,PermissionType.INVOICE_PAYMENT,ActionType.DELETE);
    }

    public boolean UserPermissionIsRead(String username){
        return getPermissionFromRepository(username,PermissionType.USER_PERMISSION,ActionType.READ);
    }
    public boolean UserPermissionIsInsert(String username){
        return getPermissionFromRepository(username,PermissionType.USER_PERMISSION,ActionType.INSERT);
    }
    public boolean UserPermissionIsUpdate(String username){
        return getPermissionFromRepository(username,PermissionType.USER_PERMISSION,ActionType.UPDATE);
    }
    public boolean UserPermissionIsDelete(String username){
        return getPermissionFromRepository(username,PermissionType.USER_PERMISSION,ActionType.DELETE);
    }
    private boolean getPermissionFromRepository(String username,
                                                PermissionType permissionType,
                                                ActionType actionType){
        Optional<UserPermissionEntity> result=userPermissionRepository.findByUser_UsernameAndPermission_PermissionType(username, permissionType);
        if (result.isEmpty()){
            return false;
        } else {
            switch (actionType) {
                case READ:
                    return result.get().isReadSet();
                case INSERT:
                    return result.get().isInsertSet();
                case UPDATE:
                    return result.get().isEditSet();
                case DELETE:
                    return result.get().isDeleteSet();
            }
        }
        return false;
    }
}
