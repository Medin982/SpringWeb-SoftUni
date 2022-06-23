package com.example.battleship.models.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class HomeDTO {

    @NotNull
    private String attackerShip;

    @NotNull
    private String defenderShip;

    public String getAttackerShip() {
        return attackerShip;
    }

    public void setAttackerShip(String attackerShip) {
        this.attackerShip = attackerShip;
    }

    public String getDefenderShip() {
        return defenderShip;
    }

    public void setDefenderShip(String defenderShip) {
        this.defenderShip = defenderShip;
    }
}
