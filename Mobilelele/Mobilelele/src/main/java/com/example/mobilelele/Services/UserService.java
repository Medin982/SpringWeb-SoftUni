package com.example.mobilelele.Services;

import com.example.mobilelele.Models.DTO.UserRegisterDTO;
import com.example.mobilelele.Models.Entity.RoleEntity;
import com.example.mobilelele.Models.Entity.UserEntity;
import com.example.mobilelele.Repository.RoleRepository;
import com.example.mobilelele.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private MobileUserDetailsService userDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public void registerUser(UserRegisterDTO registerDTO) {
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
        registerDTO.setPassword(encodedPassword);
        UserEntity mappedUser = this.modelMapper.map(registerDTO, UserEntity.class);
        Optional<RoleEntity> userRole = this.roleRepository.findByName("USER");
        mappedUser.setRole(userRole.stream().toList());
        this.userRepository.save(mappedUser);
    }

//    private void login(UserEntity userEntity) {
//        UserDetails userDetails =
//                userDetailsService.loadUserByUsername(userEntity.getEmail());
//
//        Authentication auth =
//                new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        userDetails.getPassword(),
//                        userDetails.getAuthorities()
//                );
//
//        SecurityContextHolder.
//                getContext().
//                setAuthentication(auth);
//    }
}
