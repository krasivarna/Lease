package bg.lease.model.dto;

import bg.lease.validation.FieldMatch;
import bg.lease.validation.UniqueUsername;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(
        first="password",
        second = "confirmPassword",
        message = "Passwords do not match"
)
public class UserRegisterDTO {
    @NotEmpty(message="Username should be provided")
    @UniqueUsername(message = "Username should be unique")
    private String username;

    @NotEmpty
    @Size(min=5)
    private String password;

    @NotEmpty
    @Size(min=5)
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
