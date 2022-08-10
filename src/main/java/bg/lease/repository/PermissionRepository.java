package bg.lease.repository;

import bg.lease.model.PermissionEntity;
import bg.lease.model.enums.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer> {
    Optional<PermissionEntity> findByDescription(String description);

    Optional<PermissionEntity> findByPermissionType(PermissionType permissionType);
}
