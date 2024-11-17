package bg.softuni.mobilelele.model.dtos.outputs;

import bg.softuni.mobilelele.model.entities.Offer;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Set;

public class UserProfileDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "{register.user.username.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.username.length}")
    private String username;

    @NotEmpty(message = "{register.user.password.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.password.length}")
    private String password;

    @NotEmpty(message = "{register.user.first.name.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.first.name.length}")
    private String firstName;

    @NotEmpty(message = "{register.user.last.name.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.last.name.length}")
    private String lastName;

    @Email
    @NotEmpty(message = "{register.user.password.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.password.length}")
    private String email;

    @NotNull(message = "{register.user.age.not.empty}")
    @Min(value = 18, message = "{register.user.age.min}")
    @Max(value = 100, message = "{register.user.age.max}")
    private int age;

    @NotEmpty(message = "{register.user.image.not.empty}")
    private String imageUrl;

    @NotEmpty(message = "{register.user.password.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.password.length}")
    private String newPassword;

    @NotEmpty(message = "{register.user.password.not.empty}")
    @Size(min = 2, max = 20, message = "{register.user.password.length}")
    private String confirmPassword;

    private Set<Offer> offers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
