package com.example.mobilelele.Services;

import com.example.mobilelele.Models.Entity.RoleEntity;
import com.example.mobilelele.Models.Entity.UserEntity;
import com.example.mobilelele.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MobileUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MobileUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.
                findByEmail(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email" + username + "not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return User.builder().
                username(userEntity.getUsername()).
                password(userEntity.getPassword()).
                authorities(userEntity.
                        getRole().
                        stream().
                        map(this::map).
                        toList()).
                build();
    }

    private GrantedAuthority map(RoleEntity role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getName().name());
    }
}
