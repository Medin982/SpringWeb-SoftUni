package com.example.mobilelele.Services;

import com.example.mobilelele.Models.DTO.BrandDTO;
import com.example.mobilelele.Models.DTO.ModelDTO;
import com.example.mobilelele.Models.Entity.BrandEntity;
import com.example.mobilelele.Models.Entity.ModelEntity;
import com.example.mobilelele.Repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandService(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }
    //If using ModelMapper or some other technology
    public List<BrandDTO> getAllBrands() {
        return this.brandRepository.
                findAll().
                stream().
                map(brand -> this.modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }
    // Custom mapping

//    public List<BrandDTO> getAllBrands() {
//        return brandRepository.
//                findAll().
//                stream().
//                map(this::mapBrand).
//                collect(Collectors.toList());
//    }
//
//    private BrandDTO mapBrand(BrandEntity brandEntity) {
//        List<ModelDTO> models = brandEntity.
//                getModels().
//                stream().
//                map(this::mapModel).
//                toList();
//
//        return new BrandDTO().
//                setModels(models).
//                setName(brandEntity.getName());
//    }
//
//    private ModelDTO mapModel(ModelEntity modelEntity) {
//        return new ModelDTO().
//                setId(modelEntity.getId()).
//                setName(modelEntity.getName());
//    }
}
