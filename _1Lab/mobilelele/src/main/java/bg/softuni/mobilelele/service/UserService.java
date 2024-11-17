package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dtos.inputs.UserLoginDTO;
import bg.softuni.mobilelele.model.dtos.inputs.UserRegistrationDTO;
import bg.softuni.mobilelele.model.dtos.outputs.UserProfileDTO;
import bg.softuni.mobilelele.model.entities.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistrationDTO);

    boolean loginUser(UserLoginDTO userLoginDTO);

    void logout();

    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();

    void setUserAsAdmin(long id);

    void setAdminAsUser(long id);

    void setBanUser(long id);

    void setUnbanUser(long id);

    void updateUser(UserProfileDTO userProfileDTO);

    boolean isBanned(UserLoginDTO userLoginDTO);
}
