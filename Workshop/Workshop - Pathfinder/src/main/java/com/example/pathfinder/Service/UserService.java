package com.example.pathfinder.Service;

import com.example.pathfinder.Models.CurrentUser;
import com.example.pathfinder.Models.DTO.UserRegistrationDTO;
import com.example.pathfinder.Models.User;
import com.example.pathfinder.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private void login(User user) {
        this.currentUser
                .setLogged(true);
        this.currentUser
                .setUsername(user.getUsername());
    }

}
