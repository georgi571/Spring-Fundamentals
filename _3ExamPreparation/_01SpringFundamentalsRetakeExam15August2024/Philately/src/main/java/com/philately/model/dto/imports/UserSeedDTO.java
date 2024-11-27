package com.philately.model.dto.imports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserSeedDTO implements Serializable {
    @Expose
    private String email;

    @Expose
    private String password;

    @Expose
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
