package bg.lease.model.dto;

public class UserPermissionDTO {
    private int id;
    private String username;
    private String description;
    private boolean readSet;
    private boolean insertSet;
    private boolean editSet;
    private boolean deleteSet;

    public UserPermissionDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
