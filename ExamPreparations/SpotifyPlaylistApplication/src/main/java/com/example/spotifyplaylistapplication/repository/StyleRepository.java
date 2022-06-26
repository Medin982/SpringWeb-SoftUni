package com.example.spotifyplaylistapplication.repository;

import com.example.spotifyplaylistapplication.models.entities.Style;
import com.example.spotifyplaylistapplication.models.enums.StyleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, String> {
    Optional<Style> findByName(StyleType style);
}
