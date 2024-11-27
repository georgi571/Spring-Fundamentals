package com.philately.service.impl;

import com.google.gson.Gson;
import com.philately.model.dto.*;
import com.philately.model.dto.imports.UserSeedDTO;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import com.philately.util.CurrentUser;
import com.philately.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceIml implements UserService {
    private final String PATH = "D:\\SoftwareUniversity\\Spring-Fundamentals\\_3ExamPreparation\\_01SpringFundamentalsRetakeExam15August2024\\Philately\\src\\main\\resources\\static\\files\\users.json";

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final StampRepository stampRepository;
    private final Gson gson;

    public UserServiceIml(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder, ModelMapper modelMapper, StampRepository stampRepository, Gson gson) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.stampRepository = stampRepository;
        this.gson = gson;
    }

    @Override
    public String readUserFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH)));
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() == 0) {
            UserSeedDTO[] userSeedDTOS = this.gson.fromJson(readUserFile(), UserSeedDTO[].class);
            for (UserSeedDTO seedDTO : userSeedDTOS) {
                User user = this.modelMapper.map(seedDTO, User.class);

                this.userRepository.saveAndFlush(user);
            }
        }
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {

        User user = this.userRepository
                .findByUsername(userLoginDTO.getUsername())
                .orElse(null);

        if (user == null || user.getPassword() == null || userLoginDTO.getPassword() == null) {
            return false;
        }

        boolean success = passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());

        if (success) {
            this.currentUser.setUsername(user.getUsername());
            this.currentUser.setLoggedIn(true);

        } else {
            this.currentUser.clean();
        }

        return success;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public Set<MyStampsDTO> getOwnedStamps() {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        Set<Stamp> stamps = user.getOwnedStamps();
        Set<MyStampsDTO> myStampsDTOSet = new HashSet<>();
        stamps.forEach(stamp -> {
            MyStampsDTO myStampsDTO = this.modelMapper.map(stamp, MyStampsDTO.class);
            myStampsDTOSet.add(myStampsDTO);
        });

        return myStampsDTOSet;
    }

    @Override
    public Set<MyWishedStampDTO> getWishedStamps() {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        Set<Stamp> stamps = user.getWishedStamps();
        Set<MyWishedStampDTO> myWishedStampDTOS = new HashSet<>();
        stamps.forEach(stamp -> {
            MyWishedStampDTO myWishedStampDTO = this.modelMapper.map(stamp, MyWishedStampDTO.class);
            myWishedStampDTOS.add(myWishedStampDTO);
        });
        return myWishedStampDTOS;
    }

    @Override
    public void addWishedStamp(Long id) {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        Stamp stamp = this.stampRepository.findById(id).get();
        user.getWishedStamps().add(stamp);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void logOut() {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        user.getWishedStamps().clear();
        this.userRepository.saveAndFlush(user);
        this.currentUser.clean();
    }

    @Override
    public void removeWishedStamp(Long id) {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        Stamp stamp = this.stampRepository.findById(id).get();
        user.getWishedStamps().remove(stamp);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void buyStamps() {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        for (Stamp wishedStamp : user.getWishedStamps()) {
            Stamp stamp = this.stampRepository.findById(wishedStamp.getId()).get();
            if (stamp.getBuyer() == null) {
                stamp.setBuyer(user);
                user.getPurchasedStamps().add(stamp);
                this.stampRepository.saveAndFlush(stamp);

                User seller = stamp.getOwner();
                seller.getOwnedStamps().remove(stamp);
                stamp.setOwner(null);
                this.userRepository.saveAndFlush(seller);

                this.stampRepository.saveAndFlush(stamp);
            }
        }
        user.getWishedStamps().clear();
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public Set<BoughtStampsDTO> getBoughtStamps() {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        Set<Stamp> stamps = user.getPurchasedStamps();
        Set<BoughtStampsDTO> boughtStampsDTOS = new HashSet<>();
        stamps.forEach(stamp -> {
            BoughtStampsDTO boughtStampsDTO = this.modelMapper.map(stamp, BoughtStampsDTO.class);
            boughtStampsDTOS.add(boughtStampsDTO);
        });
        return boughtStampsDTOS;
    }
}
