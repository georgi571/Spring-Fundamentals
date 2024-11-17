package bg.softuni.mobilelele.model.dtos.inputs;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public class UserRegistrationDTO implements Serializable {

    @NotEmpty(message = "{register.user.username.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.username.length}")
    private String username;

    @NotEmpty(message = "{register.user.first.name.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.first.name.length}")
    private String firstName;

    @NotEmpty(message = "{register.user.last.name.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.last.name.length}")
    private String lastName;

    @NotEmpty(message = "{register.user.password.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.password.length}")
    private String password;

    @Email
    @NotEmpty(message = "{register.user.password.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.password.length}")
    private String email;

    @NotNull(message = "{register.user.age.not.empty}")
    @Min(value = 18, message = "{register.user.age.min}")
    @Max(value = 100, message = "{register.user.age.max}")
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
