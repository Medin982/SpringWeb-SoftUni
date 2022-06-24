package com.example.coffeshop.repository;

import com.example.coffeshop.models.entities.Order;
import com.example.coffeshop.models.enums.CategoryName;
import com.example.coffeshop.models.view.OrderViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByName(String name);

    @Query("select sum(o.category.neededTime) from Order o")
    Integer getNeededTime();

    @Query("select o from Order o" +
            " order by o.price DESC")
    List<Order> findAllOrderByPriceDesc();

}
