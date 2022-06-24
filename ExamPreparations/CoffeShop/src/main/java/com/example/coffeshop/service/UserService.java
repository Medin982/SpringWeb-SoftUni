package com.example.coffeshop.service;

import com.example.coffeshop.models.dtos.UserLoginDTO;
import com.example.coffeshop.models.dtos.UserRegisterDTO;
import com.example.coffeshop.models.entities.User;
import com.example.coffeshop.models.services.UserServiceModel;
import com.example.coffeshop.models.session.LoggedUser;
import com.example.coffeshop.models.view.UserViewModel;
import com.example.coffeshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public boolean registerUser(UserRegisterDTO userRegisterDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        this.userRepository.save(user);
        return true;
    }

    public UserServiceModel login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsernameAndPassword = this.userRepository.
                findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        UserServiceModel userServiceModel = this.modelMapper.map(byUsernameAndPassword.get(), UserServiceModel.class);
        this.loggedUser.setId(userServiceModel.getId());
        this.loggedUser.setUsername(userServiceModel.getUsername());
        return userServiceModel;
    }

    public void logout() {
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    public List<UserViewModel> getAllEmployee() {
        return this.userRepository.
                findAllOrderByOrder();
    }
}
