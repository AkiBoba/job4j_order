package ru.job4j.job4j_order.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.OrderDTO;
import ru.job4j.job4j_order.model.RequestOrderDTO;
import ru.job4j.job4j_order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final DishService dishService;

    public OrderService(OrderRepository repository, DishService dishService) {
        this.repository = repository;
        this.dishService = dishService;
    }

    @Transactional
    public RequestOrderDTO create(OrderDTO order) {
        var savedOrder = repository.save(order.getOrder());
        return new RequestOrderDTO(savedOrder.getId(), order.getDish().getId());
    }

    @Transactional
    public void updateOrder(Order order) {
        repository.save(order);
    }

    public void delete(Order order) {
        repository.delete(order);
    }

    public List<Order> getAll() {
        var result = new ArrayList<Order>();
        for (var order: repository.findAll()) {
            result.add(order);
        }
        return result;
    }

    public Order getById(int id) {
        return repository.findById(id).orElseGet(null);
    }

}
