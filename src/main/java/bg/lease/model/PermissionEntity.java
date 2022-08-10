package bg.lease.model;

import bg.lease.model.enums.PermissionType;

import javax.persistence.*;

@Entity
@Table(name="permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

    public PermissionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String url) {
        this.description = description;
    }

    public PermissionEntity(String description,
                            PermissionType permissionType) {
        this.description = description;
        this.permissionType=permissionType;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }
}
