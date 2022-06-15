package com.example.pathfinder.Service;

import com.example.pathfinder.Models.CurrentUser;
import com.example.pathfinder.Models.DTO.UserLoginDTO;
import com.example.pathfinder.Models.DTO.UserRegistrationDTO;
import com.example.pathfinder.Models.User;
import com.example.pathfinder.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private Logger LOGGED = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        userRegistrationDTO.setPassword(encodedPassword);
        User user = this.modelMapper.map(userRegistrationDTO, User.class);
        this.userRepository.save(user);
        login(user);
    }

    public boolean login(UserLoginDTO userDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(userDTO.getUsername());

        if (byUsername.isEmpty()) {
            LOGGED.info("User with not found. Username: {}",
                    userDTO.getUsername());
            return false;
        }

        String password = userDTO.getPassword();
        String encodedPassword = byUsername.get().getPassword();
        boolean isSame = passwordEncoder.matches(password, encodedPassword);

        if (isSame) {
            login(byUsername.get());
        } else {
            logout();
        }
        return isSame;
    }

    private void login(User user) {
        this.currentUser
                .setLogged(true);
        this.currentUser
                .setUsername(user.getUsername());
    }

    public void logout() {
        this.currentUser.clear();
    }

    public User getLoggedUser() {
        if (this.currentUser.isLogged()) {
            String username = currentUser.getUsername();
            Optional<User> byUsername = this.userRepository.findByUsername(username);
            return byUsername.get();
        }
        return null;
    }
}
