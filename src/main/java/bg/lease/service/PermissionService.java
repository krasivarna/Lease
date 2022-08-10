package bg.lease.service;

import bg.lease.model.PermissionEntity;
import bg.lease.model.dto.PermissionDTO;
import bg.lease.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    private PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<PermissionDTO> listPermissions() {
        return this.permissionRepository.findAll().stream().
                map(this::map).collect(Collectors.toList());
    }

    private PermissionDTO map(PermissionEntity permission) {
        PermissionDTO result = new PermissionDTO();
        result.setId(permission.getId());
        result.setDescription(permission.getDescription());
        result.setPermissionType(permission.getPermissionType());
        return result;
    }
}
