package com.philately.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UserLoginDTO implements Serializable {

    @NotEmpty(message = "{username.empty}")
    @Size(min = 3, max = 20, message = "{username.length}")
    private String username;

    @NotEmpty(message = "{password.empty}")
    @Size(min = 3, max = 20, message = "{password.length}")
    private String password;

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
}
