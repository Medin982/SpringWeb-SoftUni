package com.example.battleship.services;

import com.example.battleship.models.dtos.AddShipDTO;
import com.example.battleship.models.dtos.HomeDTO;
import com.example.battleship.models.entities.Ship;
import com.example.battleship.models.entities.User;
import com.example.battleship.models.enums.CategoryEnums;
import com.example.battleship.models.services.ShipViewModel;
import com.example.battleship.models.session.LoggedUser;
import com.example.battleship.repository.CategoryRepository;
import com.example.battleship.repository.ShipRepository;
import com.example.battleship.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        ship.setUser(this.userRepository.getById(this.loggedUser.getId()));
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

    public List<ShipViewModel> findAllById(String id) {
        return this.shipRepository.
                findAllByUserId(id)
                .stream().
                map(ship -> this.modelMapper.map(ship, ShipViewModel.class))
                .collect(Collectors.toList());
    }

    public List<ShipViewModel> findAllByOtherId(String id) {
        Optional<User> user = this.userRepository.findById(id);
        return this.shipRepository.
                findAllByUserIsNot(user.get()).
                stream().
                map(ship -> this.modelMapper.map(ship, ShipViewModel.class)).
                collect(Collectors.toList());
    }

    public List<ShipViewModel> findAll() {
        return this.shipRepository.
                findAll()
                .stream().
                map(ship -> this.modelMapper.map(ship, ShipViewModel.class))
                .collect(Collectors.toList());
    }

    public void fight(HomeDTO homeDTO) {

    }
}
