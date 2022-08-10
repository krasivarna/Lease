package bg.lease.model.dto;

import bg.lease.model.enums.PermissionType;

public class PermissionDTO {
    private int id;
    private String description;
    private PermissionType permissionType;

    public PermissionDTO() {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }
}
