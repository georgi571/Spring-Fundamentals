package com.philately.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_wished_stamps",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "stamp_id", referencedColumnName = "id"))
    private Set<Stamp> wishedStamps;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private Set<Stamp> purchasedStamps;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Stamp> ownedStamps;

    public User() {
        this.wishedStamps = new HashSet<>();
        this.purchasedStamps = new HashSet<>();
        this.ownedStamps = new HashSet<>();
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

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public Set<Stamp> getWishedStamps() {
        return wishedStamps;
    }

    public void setWishedStamps(Set<Stamp> wishedStamps) {
        this.wishedStamps = wishedStamps;
    }

    public Set<Stamp> getPurchasedStamps() {
        return purchasedStamps;
    }

    public void setPurchasedStamps(Set<Stamp> purchasedStamps) {
        this.purchasedStamps = purchasedStamps;
    }

    public Set<Stamp> getOwnedStamps() {
        return ownedStamps;
    }

    public void setOwnedStamps(Set<Stamp> ownedStamps) {
        this.ownedStamps = ownedStamps;
    }
}
