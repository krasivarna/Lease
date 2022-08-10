package bg.lease.repository;

import bg.lease.model.UserPermissionEntity;
import bg.lease.model.enums.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity,Integer> {

    Optional<UserPermissionEntity> findByUser_UsernameAndPermission_PermissionType(String username, PermissionType permissionType);
}
