package bg.lease.model;

import javax.persistence.*;

@Entity
@Table(name="userpermission")
public class UserPermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private PermissionEntity permission;

    private boolean readSet;
    private boolean insertSet;
    private boolean editSet;
    private boolean deleteSet;

    public UserPermissionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(PermissionEntity permission) {
        this.permission = permission;
    }

    public boolean isReadSet() {
        return readSet;
    }

    public void setReadSet(boolean readSet) {
        this.readSet = readSet;
    }

    public boolean isInsertSet() {
        return insertSet;
    }

    public void setInsertSet(boolean insertSet) {
        this.insertSet = insertSet;
    }

    public boolean isEditSet() {
        return editSet;
    }

    public void setEditSet(boolean editSet) {
        this.editSet = editSet;
    }

    public boolean isDeleteSet() {
        return deleteSet;
    }

    public void setDeleteSet(boolean deleteSet) {
        this.deleteSet = deleteSet;
    }

    public UserPermissionEntity(UserEntity user, PermissionEntity permission, boolean readSet, boolean insertSet, boolean editSet, boolean deleteSet) {
        this.user = user;
        this.permission = permission;
        this.readSet = readSet;
        this.insertSet = insertSet;
        this.editSet = editSet;
        this.deleteSet = deleteSet;
    }
}
