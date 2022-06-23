package com.example.battleship.services;

import com.example.battleship.models.dtos.LoginDTO;
import com.example.battleship.models.dtos.RegisterDTO;
import com.example.battleship.models.services.ShipViewModel;
import com.example.battleship.models.entities.User;
import com.example.battleship.models.session.LoggedUser;
import com.example.battleship.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public boolean registerUser(RegisterDTO registerDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(registerDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registerDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        String encodedPassword = this.passwordEncoder.encode(registerDTO.getPassword());
        registerDTO.setPassword(encodedPassword);
        User user = this.modelMapper.map(registerDTO, User.class);
        this.userRepository.save(user);
        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> user = this.userRepository.findByUsername(loginDTO.getUsername());
        if (user.isEmpty()) {
            return false;
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            return false;
        }

        this.loggedUser.setId(user.get().getId());
        this.loggedUser.setFullName(user.get().getFullName());
        return true;
    }

    public void logout() {
        this.loggedUser.logout();
    }
}
