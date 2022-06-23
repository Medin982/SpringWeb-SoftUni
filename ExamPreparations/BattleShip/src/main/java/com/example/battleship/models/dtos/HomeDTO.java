package com.example.battleship.models.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class HomeDTO {

    @NotNull
    private UUID attackerShip;

    @NotNull
    private UUID defenderShip;

    public UUID getAttackerShip() {
        return attackerShip;
    }

    public void setAttackerShip(UUID attackerShip) {
        this.attackerShip = attackerShip;
    }

    public UUID getDefenderShip() {
        return defenderShip;
    }

    public void setDefenderShip(UUID defenderShip) {
        this.defenderShip = defenderShip;
    }
}
