package judgev2.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static judgev2.constants.ErrorMessages.*;
import static judgev2.constants.GlobalConstants.GITHUB_REGEX;

public class UserAddBindingModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String git;

    public UserAddBindingModel() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Email(message = INVALID_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = GITHUB_REGEX, message = INVALID_GITHUB_URL)
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
