package com.example.spotifyplaylistapplication.service;

import com.example.spotifyplaylistapplication.models.dtos.AddSongDTO;
import com.example.spotifyplaylistapplication.models.dtos.SongDTO;
import com.example.spotifyplaylistapplication.models.entities.Song;
import com.example.spotifyplaylistapplication.models.entities.Style;
import com.example.spotifyplaylistapplication.models.enums.StyleType;
import com.example.spotifyplaylistapplication.repository.SongRepository;
import com.example.spotifyplaylistapplication.repository.StyleRepository;
import com.example.spotifyplaylistapplication.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;

    private final UserRepository userRepository;

    private final StyleRepository styleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SongService(SongRepository songRepository, UserRepository userRepository, StyleRepository styleRepository, ModelMapper modelMapper) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
        this.modelMapper = modelMapper;
    }

    public boolean addSong(AddSongDTO addSongDTO) {
        Optional<Song> byPerformer = this.songRepository.findByPerformer(addSongDTO.getPerformer());
        if (byPerformer.isPresent()) {
            return false;
        }
        Optional<Style> style = this.styleRepository.findByName(addSongDTO.getStyle());
        Song song = this.modelMapper.map(addSongDTO, Song.class);
        song.setStyle(style.get());
        this.songRepository.save(song);
        return true;
    }

    public List<SongDTO> getByStyle(StyleType type) {
        return this.songRepository.
                findAllByStyle_Name(type).
                stream().
                map(song -> this.modelMapper.map(song, SongDTO.class))
                .collect(Collectors.toList());
    }
}
