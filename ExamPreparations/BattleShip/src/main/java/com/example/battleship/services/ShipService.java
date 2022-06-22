package com.example.battleship.services;

import com.example.battleship.models.dtos.AddShipDTO;
import com.example.battleship.models.entities.Category;
import com.example.battleship.models.entities.Ship;
import com.example.battleship.models.enums.CategoryEnums;
import com.example.battleship.models.session.LoggedUser;
import com.example.battleship.repository.CategoryRepository;
import com.example.battleship.repository.ShipRepository;
import com.example.battleship.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository, LoggedUser loggedUser, ModelMapper modelMapper,
                       UserRepository userRepository, CategoryRepository categoryRepository) {
        this.shipRepository = shipRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public boolean addShip(AddShipDTO addShipDTO) {
        Optional<Ship> shipByName = this.shipRepository.findByName(addShipDTO.getName());
        if (shipByName.isPresent()) {
            return false;
        }
        CategoryEnums type = getCategoryType(addShipDTO.getCategory());
        Ship ship = this.modelMapper.map(addShipDTO, Ship.class);
        ship.setCategory(this.categoryRepository.findByName(type));
        ship.setUser(this.userRepository.getReferenceById(this.loggedUser.getId()));
        this.shipRepository.save(ship);
        return true;
    }

    private CategoryEnums getCategoryType(Integer category) {
        return switch (category) {
            case 0 -> CategoryEnums.BATTLE;
            case 1 -> CategoryEnums.CARGO;
            case 2 -> CategoryEnums.PATROL;
            default -> null;
        };
    }
}
