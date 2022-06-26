package com.example.spotifyplaylistapplication.web;

import com.example.spotifyplaylistapplication.models.enums.StyleType;
import com.example.spotifyplaylistapplication.models.session.LoggedUser;
import com.example.spotifyplaylistapplication.service.SongService;
import com.example.spotifyplaylistapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final SongService songService;

    private final UserService userService;

    @Autowired
    public HomeController(LoggedUser loggedUser, SongService songService, UserService userService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        if (loggedUser.getId() == null) {
            return "index";
        }
        model.addAttribute("popSongs", this.songService.getByStyle(StyleType.POP));
        model.addAttribute("jazzSongs", this.songService.getByStyle(StyleType.JAZZ));
        model.addAttribute("rockSongs", this.songService.getByStyle(StyleType.ROCK));
        model.addAttribute("userPlayList", this.userService.getPlaylist(loggedUser.getId()));
        model.addAttribute("totalMin", this.userService.getTotalMin(loggedUser.getId()));
        return "home";
    }

}
