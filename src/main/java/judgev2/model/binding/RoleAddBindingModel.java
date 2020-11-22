package judgev2.model.binding;

import org.hibernate.validator.constraints.Length;

import static judgev2.constants.ErrorMessages.INVALID_USERNAME;

public class RoleAddBindingModel {

    private String username;
    private String role;

    public RoleAddBindingModel() {
    }

    @Length(min = 2, max = 10, message = INVALID_USERNAME)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
