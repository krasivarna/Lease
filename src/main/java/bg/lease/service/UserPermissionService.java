package bg.lease.service;

import bg.lease.model.CountryEntity;
import bg.lease.model.PermissionEntity;
import bg.lease.model.UserEntity;
import bg.lease.model.UserPermissionEntity;
import bg.lease.model.dto.UserPermissionDTO;
import bg.lease.repository.PermissionRepository;
import bg.lease.repository.UserPermissionRepository;
import bg.lease.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPermissionService {

    private UserPermissionRepository userPermissionRepository;
    private UserRepository userRepository;
    private PermissionRepository permissionRepository;

    public UserPermissionService(UserPermissionRepository userPermissionRepository,
                                 UserRepository userRepository,
                                 PermissionRepository permissionRepository) {
        this.userPermissionRepository = userPermissionRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<UserPermissionDTO> listPermissions() {
        return this.userPermissionRepository.findAll().stream().
                map(this::map).collect(Collectors.toList());
    }

    private UserPermissionDTO map(UserPermissionEntity permission) {
        UserPermissionDTO result = new UserPermissionDTO();
        result.setId(permission.getId());
        result.setUsername(permission.getUser().getUsername());
        result.setDescription(permission.getPermission().getDescription());
        result.setReadSet(permission.isReadSet());
        result.setInsertSet(permission.isInsertSet());
        result.setEditSet(permission.isEditSet());
        result.setDeleteSet(permission.isDeleteSet());
        return result;
    }

    public void addCard(UserPermissionDTO userPermissionDTO) {
        Optional<UserPermissionEntity> byId=this.userPermissionRepository.findById(userPermissionDTO.getId());

        UserPermissionEntity userpermission;
        if (byId.isPresent()){
            //throw  new RuntimeException("vendor no");
            userpermission=byId.get();
        } else {
            userpermission = new UserPermissionEntity();
        }

        Optional<UserEntity> byName=this.userRepository.findByUsername(userPermissionDTO.getUsername());
        if (byName.isEmpty()){
            throw new RuntimeException("user "+userPermissionDTO.getUsername()+" is missing");
        }

        Optional<PermissionEntity> byNo=this.permissionRepository.findByDescription(userPermissionDTO.getDescription());
        if (byNo.isEmpty()){
            throw new RuntimeException("permission "+userPermissionDTO.getDescription()+" is missing");
        }
        userpermission.setUser(byName.get());
        userpermission.setPermission(byNo.get());
        userpermission.setReadSet(userPermissionDTO.isReadSet());
        userpermission.setInsertSet(userPermissionDTO.isInsertSet());
        userpermission.setEditSet(userPermissionDTO.isEditSet());
        userpermission.setDeleteSet(userPermissionDTO.isDeleteSet());

        userPermissionRepository.save(userpermission);
    }

    public UserPermissionDTO editCard(Integer id) {
        Optional<UserPermissionEntity> optUserPermission=userPermissionRepository.findById(id);
        if (optUserPermission.isEmpty()){
            throw new RuntimeException("user permission "+id+" is not found");
        }
        UserPermissionEntity userpermission=optUserPermission.get();
        return map(userpermission);
    }

    @Transactional
    public void deleteCard(Integer id) {
        userPermissionRepository.deleteById(id);
    }
}
