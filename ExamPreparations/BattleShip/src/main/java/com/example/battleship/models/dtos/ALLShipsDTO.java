package com.example.battleship.models.dtos;

import java.util.List;

public class ALLShipsDTO {

    private List<ShipDTO> ships;

    public List<ShipDTO> getShips() {
        return ships;
    }

    public void setShips(List<ShipDTO> ships) {
        this.ships = ships;
    }
}
