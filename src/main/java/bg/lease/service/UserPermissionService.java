package bg.lease.service;

import bg.lease.model.UserPermissionEntity;
import bg.lease.model.dto.UserPermissionDTO;
import bg.lease.repository.UserPermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPermissionService {

    private UserPermissionRepository userPermissionRepository;

    public UserPermissionService(UserPermissionRepository userPermissionRepository) {
        this.userPermissionRepository = userPermissionRepository;
    }

    public List<UserPermissionDTO> listPermissions() {
        return this.userPermissionRepository.findAll().stream().
                map(this::map).collect(Collectors.toList());
    }

    private UserPermissionDTO map(UserPermissionEntity permission) {
        UserPermissionDTO result = new UserPermissionDTO();
        result.setId(permission.getId());
        result.setUsername(permission.getUser().getUsername());
        result.setUrl(permission.getPermission().getUrl());
        result.setReadSet(permission.isReadSet());
        result.setInsertSet(permission.isInsertSet());
        result.setEditSet(permission.isEditSet());
        result.setDeleteSet(permission.isDeleteSet());
        return result;
    }
}
