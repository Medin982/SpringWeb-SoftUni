package com.example.spotifyplaylistapplication.repository;

import com.example.spotifyplaylistapplication.models.dtos.SongDTO;
import com.example.spotifyplaylistapplication.models.entities.Song;
import com.example.spotifyplaylistapplication.models.enums.StyleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, String> {
    Optional<Song> findByPerformer(String performer);

    List<Song> findAllByStyle_Name(StyleType type);
}
