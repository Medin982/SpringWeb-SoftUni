package com.example.spotifyplaylistapplication.web;

import com.example.spotifyplaylistapplication.models.dtos.AddSongDTO;
import com.example.spotifyplaylistapplication.service.SongService;
import com.example.spotifyplaylistapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/song")
public class SongController {

    private final SongService songService;

    private final UserService userService;

    @Autowired
    public SongController(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addSong(Model model) {
        if (!model.containsAttribute("addSongDTO")) {
            model.addAttribute("addSongDTO", new AddSongDTO());
        }
        return "song-add";
    }

    @PostMapping("/add")
    public String addSong(@Valid AddSongDTO addSongDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !this.songService.addSong(addSongDTO)) {
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", bindingResult);
            return "redirect:add";
        }
        return "redirect:/";
    }

    @GetMapping("/addToPlaylist/{id}")
    public String addToPlaylist(@PathVariable String id) {
        this.userService.addSongToPlaylist(id);
        return "redirect:/";
    }

    @GetMapping("/remove")
        public String remove () {
         this.userService.removePlaylist();
        return "redirect:/";
    }
}
