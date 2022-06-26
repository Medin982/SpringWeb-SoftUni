package com.example.spotifyplaylistapplication.service;

import com.example.spotifyplaylistapplication.models.dtos.SongDTO;
import com.example.spotifyplaylistapplication.models.dtos.UserLoginDTO;
import com.example.spotifyplaylistapplication.models.dtos.UserRegisterDTO;
import com.example.spotifyplaylistapplication.models.entities.Song;
import com.example.spotifyplaylistapplication.models.entities.User;
import com.example.spotifyplaylistapplication.models.session.LoggedUser;
import com.example.spotifyplaylistapplication.repository.SongRepository;
import com.example.spotifyplaylistapplication.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final LoggedUser loggedUser;

    private final SongRepository songRepository;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.songRepository = songRepository;
    }

    public boolean registerUser(UserRegisterDTO userRegisterDTO) {
        Optional<User> byUsername = this.userRepository.
                findByUsernameAndEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());
        if (byUsername.isPresent()) {
            return false;
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        this.userRepository.save(user);
        return true;
    }

    public boolean loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(userLoginDTO.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }
        this.loggedUser.setId(byUsername.get().getId());
        this.loggedUser.setUsername(byUsername.get().getUsername());
        return true;
    }

    public void logout() {
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    public List<SongDTO> getPlaylist(String id) {
        return this.userRepository.
                findById(id).
                get().
                getPlaylist().
                stream().
                map(song -> this.modelMapper.map(song, SongDTO.class)).
               collect(Collectors.toList());
    }

    public Double getTotalMin(String id) {
        Optional<User> user = this.userRepository.findById(id);
        Double min = user.get().
                getPlaylist().
                stream().
                map(s -> {
                    double total = 0;
                    total += s.getDuration();
                    return total;
                })
                .findFirst().orElse(0.0);
        return min;
    }

    public void addSongToPlaylist(String id) {
        Optional<Song> song = this.songRepository.findById(id);
        Optional<User> user = this.userRepository.findById(loggedUser.getId());
        user.get().getPlaylist().add(song.get());
        this.userRepository.save(user.get());
    }

    public void removePlaylist() {
        Optional<User> user = this.userRepository.findById(loggedUser.getId());
        user.get().setPlaylist(new ArrayList<>());
        this.userRepository.save(user.get());
    }
}
