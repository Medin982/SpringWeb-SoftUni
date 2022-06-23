package com.example.shoppinglist.service;

import com.example.shoppinglist.models.dtos.UserRegisterDTO;
import com.example.shoppinglist.models.entities.User;
import com.example.shoppinglist.models.services.UserServiceModel;
import com.example.shoppinglist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }
        Optional<User> byUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        this.userRepository.save(this.modelMapper.map(userRegisterDTO, User.class));
        return true;
    }

    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.userRepository.
                findByUsernameAndPassword(username, password).
                map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }
}
