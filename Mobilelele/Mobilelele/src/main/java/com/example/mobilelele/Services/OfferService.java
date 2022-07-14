package com.example.mobilelele.Services;

import com.example.mobilelele.Models.DTO.AddOfferDTO;
import com.example.mobilelele.Models.DTO.OfferDTO;
import com.example.mobilelele.Models.DTO.OfferDetailsDTO;
import com.example.mobilelele.Models.Entity.ModelEntity;
import com.example.mobilelele.Models.Entity.OfferEntity;
import com.example.mobilelele.Models.Entity.UserEntity;
import com.example.mobilelele.Repository.ModelRepository;
import com.example.mobilelele.Repository.OfferRepository;
import com.example.mobilelele.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferService(OfferRepository offerRepository,
                        UserRepository userRepository, ModelRepository modelRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity offer = this.modelMapper.map(addOfferDTO, OfferEntity.class);

        ModelEntity model = this.modelRepository.findById(addOfferDTO.getModelId()).
                orElseThrow();

//        offer.setSeller(seller);
        offer.setModel(model);
        this.offerRepository.save(offer);
    }

    public List<OfferDTO> getAllOffers() {
        return this.offerRepository.findAll()
                .stream().
                map(offer -> {
                    String brandName = offer.getModel().getBrand().getName();
                    OfferDTO currentOffer = this.modelMapper.map(offer, OfferDTO.class);
                    currentOffer.setBrandName(brandName);
                    return currentOffer;
                }).toList();
    }

    public OfferDetailsDTO getDetailsOffers(Long id) {
        return this.offerRepository.
                findById(id).
                map(offer -> {
                    String brandName = offer.getModel().getBrand().getName();
                    OfferDetailsDTO currentOffer = this.modelMapper.map(offer, OfferDetailsDTO.class);
                    currentOffer.setBrandName(brandName);
                    return currentOffer;
                }).
                orElseThrow();
    }

    public void updateOffer(AddOfferDTO addOfferDTO, Long id) {
        Optional<OfferEntity> updateOffer = this.offerRepository.findById(id).
                map(offer -> {
                    offer.setDescription(addOfferDTO.getDescription());
                    offer.setEngine(addOfferDTO.getEngine());
                    offer.setMileage(addOfferDTO.getMileage());
                    offer.setImageUrl(addOfferDTO.getImageURL());
                    offer.setPrice(addOfferDTO.getPrice());
                    offer.setTransmission(addOfferDTO.getTransmission());
                    offer.setYear(addOfferDTO.getYear());
                    return offer;
                });
        this.offerRepository.save(updateOffer.get());
    }
}
