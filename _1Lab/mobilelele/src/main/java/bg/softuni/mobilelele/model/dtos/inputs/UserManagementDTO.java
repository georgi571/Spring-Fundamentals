package bg.softuni.mobilelele.model.dtos.inputs;

import bg.softuni.mobilelele.model.entities.Role;

import java.io.Serializable;

public class UserManagementDTO implements Serializable {

    private long id;

    private String username;

    private Role role;

    private boolean isBanned;

    private boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
