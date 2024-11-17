package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.dtos.inputs.UserLoginDTO;
import bg.softuni.mobilelele.model.dtos.inputs.UserRegistrationDTO;
import bg.softuni.mobilelele.model.dtos.outputs.UserProfileDTO;
import bg.softuni.mobilelele.model.entities.Role;
import bg.softuni.mobilelele.model.entities.User;
import bg.softuni.mobilelele.model.enums.UserRole;
import bg.softuni.mobilelele.repository.RoleRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.CurrentUser;
import bg.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        User user = this.modelMapper.map(userRegistrationDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(userRegistrationDTO.getPassword()));
        Role role = this.roleRepository.findByRole(UserRole.USER);
        if (this.userRepository.count() == 0) {
            role = this.roleRepository.findByRole(UserRole.ADMIN);
        }
        user.setRole(role);
        user.setBanned(false);
        user.setCreated(LocalDateTime.now());
        user.setActive(false);
        user.setImageUrl("https://cdn.pixabay.com/photo/2017/11/10/05/48/user-2935527_1280.png");
        this.userRepository.saveAndFlush(user);
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
            this.currentUser.setFirstName(user.getFirstName());
            this.currentUser.setFullName(user.getFirstName() + " " + user.getLastName());
            this.currentUser.setLoggedIn(true);

            if (user.getRole().getRole() == UserRole.ADMIN) {
                this.currentUser.setAdmin(true);
            }

            user.setActive(true);
            this.userRepository.saveAndFlush(user);

        } else {
            this.currentUser.clean();
        }

        return success;
    }

    @Override
    public void logout() {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        user.setActive(false);
        this.userRepository.saveAndFlush(user);
        this.currentUser.clean();
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
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void setUserAsAdmin(long id) {
        User user = this.userRepository.findById(id).get();
        Role role = this.roleRepository.findByRole(UserRole.ADMIN);
        user.setRole(role);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void setAdminAsUser(long id) {
        User user = this.userRepository.findById(id).get();
        Role role = this.roleRepository.findByRole(UserRole.USER);
        user.setRole(role);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void setBanUser(long id) {
        User user = this.userRepository.findById(id).get();
        user.setBanned(true);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void setUnbanUser(long id) {
        User user = this.userRepository.findById(id).get();
        user.setBanned(false);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void updateUser(UserProfileDTO userProfileDTO) {
        User user = this.userRepository.findById(userProfileDTO.getId()).get();
        user.setFirstName(userProfileDTO.getFirstName());
        user.setLastName(userProfileDTO.getLastName());
        user.setUsername(userProfileDTO.getUsername());
        user.setEmail(userProfileDTO.getEmail());
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean isBanned(UserLoginDTO userLoginDTO) {
        User user = this.userRepository
                .findByUsername(userLoginDTO.getUsername())
                .orElse(null);

        if (user.isBanned()) {
            this.currentUser.clean();
            return true;
        }

        return false;
    }
}
