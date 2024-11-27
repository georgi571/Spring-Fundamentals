package com.philately.model.dto;

import com.philately.validation.annotation.PasswordMatches;
import com.philately.validation.annotation.UniqueEmail;
import com.philately.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@PasswordMatches
public class UserRegisterDTO implements Serializable {

    @UniqueUsername
    @NotEmpty(message = "{username.empty}")
    @Size(min = 3, max = 20, message = "{username.length}")
    private String username;

    @UniqueEmail
    @Email(message = "{email.empty}")
    @NotEmpty(message = "{email.empty}")
    private String email;

    @NotEmpty(message = "{password.empty}")
    @Size(min = 3, max = 20, message = "{password.length}")
    private String password;

    @NotEmpty(message = "{confirm.password.empty}")
    @Size(min = 3, max = 20, message = "{password.length}")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
