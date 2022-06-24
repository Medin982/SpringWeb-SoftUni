package com.example.coffeshop.repository;

import com.example.coffeshop.models.entities.User;
import com.example.coffeshop.models.view.UserViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String userName, String password);

    @Query("select new com.example.coffeshop.models.view.UserViewModel(" +
            "u.id, u.username, u.orderList.size) from User u" +
            " order by u.orderList.size desc")
    List<UserViewModel> findAllOrderByOrder();
}
