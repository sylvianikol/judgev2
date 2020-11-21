package judgev2.model.binding;

import org.hibernate.validator.constraints.Length;

import static judgev2.constants.ErrorMessages.INVALID_PASSWORD;
import static judgev2.constants.ErrorMessages.INVALID_USERNAME;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @Length(min = 2, max = 10, message = INVALID_USERNAME)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, max = 10, message = INVALID_PASSWORD)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
