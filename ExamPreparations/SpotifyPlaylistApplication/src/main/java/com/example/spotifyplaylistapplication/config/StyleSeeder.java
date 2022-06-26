package com.example.spotifyplaylistapplication.config;

import com.example.spotifyplaylistapplication.models.entities.Style;
import com.example.spotifyplaylistapplication.models.enums.StyleType;
import com.example.spotifyplaylistapplication.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StyleSeeder implements CommandLineRunner {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleSeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.styleRepository.count() <= 0) {
            List<Style> styles = Arrays.stream(StyleType.values()).
                    map(Style::new).toList();
            this.styleRepository.saveAll(styles);
        }
    }
}
