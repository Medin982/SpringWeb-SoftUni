package com.example.coffeshop.service;

import com.example.coffeshop.models.dtos.AddOrderDTO;
import com.example.coffeshop.models.entities.Category;
import com.example.coffeshop.models.entities.Order;
import com.example.coffeshop.models.entities.User;
import com.example.coffeshop.models.enums.CategoryName;
import com.example.coffeshop.models.session.LoggedUser;
import com.example.coffeshop.models.view.OrderViewModel;
import com.example.coffeshop.repository.CategoryRepository;
import com.example.coffeshop.repository.OrderRepository;
import com.example.coffeshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, CategoryRepository categoryRepository,
                        LoggedUser loggedUser, UserRepository userRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public boolean addOrder(AddOrderDTO addOrderDTO) {
        Optional<Order> byName = this.orderRepository.findByName(addOrderDTO.getName());
        if (byName.isPresent()) {
            return false;
        }
        Optional<Category> type = this.categoryRepository.findByName(addOrderDTO.getCategory());
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Order order = this.modelMapper.map(addOrderDTO, Order.class);
        order.setCategory(type.get());
        order.setEmployee(user.get());
        this.orderRepository.save(order);

        return true;
    }

    public Integer getPrepareTimeForAllOrders() {
        if (this.orderRepository.getNeededTime() == null) {
            return 0;
        }
        return this.orderRepository.getNeededTime();
    }

    public List<OrderViewModel> getAllOrders() {
        return this.orderRepository.
                findAllOrderByPriceDesc().
                stream().
                map(order -> this.modelMapper.map(order, OrderViewModel.class)).
                collect(Collectors.toList());
    }

    public void removeOrder(long id) {
        this.orderRepository.deleteById(id);
    }
}
