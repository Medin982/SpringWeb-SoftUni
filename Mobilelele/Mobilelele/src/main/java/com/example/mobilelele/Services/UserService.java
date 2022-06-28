package com.example.mobilelele.Services;

import com.example.mobilelele.Models.CurrentUser;
import com.example.mobilelele.Models.DTO.UserLoginDTO;
import com.example.mobilelele.Models.DTO.UserRegisterDTO;
import com.example.mobilelele.Models.Entity.UserEntity;
import com.example.mobilelele.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser,
                       PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> user = this.userRepository.findByEmail(userLoginDTO.getEmail());
        if (user.isEmpty()) {
            return false;
        }
        String password = userLoginDTO.getPassword();
        String encodedPassword = user.get().getPassword();

        if (!passwordEncoder.matches(password, encodedPassword)) {
            return false;
        }
        this.currentUser.setName(user.get().getFirstName() + " " + user.get().getLastName());
        this.currentUser.setId(user.get().getId());
        return true;
    }

    public void logout() {
        this.currentUser.clear();
    }

    public void registerUser(UserRegisterDTO registerDTO) {
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
        registerDTO.setPassword(encodedPassword);
        UserEntity mappedUser = this.modelMapper.map(registerDTO, UserEntity.class);
        this.userRepository.save(mappedUser);
    }
}
