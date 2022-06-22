package com.example.battleship.repository;

import com.example.battleship.models.entities.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipRepository extends JpaRepository<Ship, UUID> {
    Optional<Ship> findByName(String name);
}
