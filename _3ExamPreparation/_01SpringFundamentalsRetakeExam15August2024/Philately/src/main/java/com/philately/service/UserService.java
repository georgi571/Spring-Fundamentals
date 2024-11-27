package com.philately.service;

import com.philately.model.dto.*;
import com.philately.model.entity.User;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    String readUserFile() throws IOException;

    void seedUsers() throws IOException;

    boolean loginUser(UserLoginDTO userLoginDTO);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    void registerUser(UserRegisterDTO userRegisterDTO);

    Set<MyStampsDTO> getOwnedStamps();

    Set<MyWishedStampDTO> getWishedStamps();

    void addWishedStamp(Long id);

    void logOut();

    void removeWishedStamp(Long id);

    void buyStamps();

    Set<BoughtStampsDTO> getBoughtStamps();
}
