package com.example.mobilelele.Services;

import com.example.mobilelele.Models.CurrentUser;
import com.example.mobilelele.Models.DTO.AddOfferDTO;
import com.example.mobilelele.Models.Entity.ModelEntity;
import com.example.mobilelele.Models.Entity.OfferEntity;
import com.example.mobilelele.Models.Entity.UserEntity;
import com.example.mobilelele.Repository.ModelRepository;
import com.example.mobilelele.Repository.OfferRepository;
import com.example.mobilelele.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final CurrentUser currentUser;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferService(CurrentUser currentUser, OfferRepository offerRepository,
                        UserRepository userRepository, ModelRepository modelRepository, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity offer = this.modelMapper.map(addOfferDTO, OfferEntity.class);

        UserEntity seller = this.userRepository.findByEmail(this.currentUser.getEmail())
                .orElseThrow();
        ModelEntity model = this.modelRepository.findById(addOfferDTO.getModelId()).
                orElseThrow();

        offer.setSeller(seller);
        offer.setModel(model);
        this.offerRepository.save(offer);
    }
}
