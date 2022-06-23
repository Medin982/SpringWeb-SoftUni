package com.example.shoppinglist.service;

import com.example.shoppinglist.models.dtos.ProductDTO;
import com.example.shoppinglist.models.entities.Category;
import com.example.shoppinglist.models.entities.Product;
import com.example.shoppinglist.models.enums.CategoryName;
import com.example.shoppinglist.models.view.ProductViewModel;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    public void addProduct(ProductDTO productDTO) {
        Optional<Category> category = this.categoryRepository.findAllByName(productDTO.getCategory());
        Product product = this.modelMapper.map(productDTO, Product.class);
        product.setCategory(category.get());
        this.productRepository.save(product);
    }

    public BigDecimal getTotalSum() {
        return this.productRepository.findTotalPriceSum();
    }

    public List<ProductViewModel> getProducts(CategoryName name) {
        return this.productRepository.
                findByCategory_Name(name).
                stream().
                map(product -> this.modelMapper.map(product, ProductViewModel.class)).
                collect(Collectors.toList());
    }

    public void buyProduct(String id) {
        this.productRepository.deleteById(id);
    }

    public void buyAll() {
        this.productRepository.deleteAll();
    }
}
