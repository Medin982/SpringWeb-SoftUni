package com.example.battleship.repository;

import com.example.battleship.models.entities.Ship;
import com.example.battleship.models.entities.User;
import com.example.battleship.models.services.ShipViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipRepository extends JpaRepository<Ship, String> {
    Optional<Ship> findByName(String name);

    List<Ship> findAllByUserId(String id);

    List<Ship> findAllByUserIsNot(User user);
}
