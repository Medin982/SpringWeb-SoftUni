package com.example.spotifyplaylistapplication.models.dtos;

import java.util.List;

public class UserDTO {

    private String id;

    private String username;

    private List<SongDTO> playlist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SongDTO> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<SongDTO> playlist) {
        this.playlist = playlist;
    }
}
