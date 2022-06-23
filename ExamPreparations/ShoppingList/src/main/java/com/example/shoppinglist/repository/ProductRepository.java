package com.example.shoppinglist.repository;

import com.example.shoppinglist.models.entities.Product;
import com.example.shoppinglist.models.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("select sum(p.price) from Product p")
    BigDecimal findTotalPriceSum();

    List<Product> findByCategory_Name(CategoryName name);
}
