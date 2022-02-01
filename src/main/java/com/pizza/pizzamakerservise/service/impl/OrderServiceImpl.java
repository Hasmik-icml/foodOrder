package com.pizza.pizzamakerservise.service.impl;

import com.pizza.pizzamakerservise.model.Order;
import com.pizza.pizzamakerservise.model.Table;
import com.pizza.pizzamakerservise.repository.OrderRepository;
import com.pizza.pizzamakerservise.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository = new OrderRepository();

    @Override
    public Order read(int table_id) {
        return orderRepository.read(table_id);
    }

    @Override
    public List<Order> readAll() {
        return orderRepository.readAll();
    }

    @Override
    public void create(Order order) {
        if (order == null){
            return;
        }
        orderRepository.create(order);

    }

    @Override
    public Order update(int id, Order order) {

        Order read = read(id);///??????
        if (read != null) {
            return orderRepository.update(id, order);
        }
        return null;

    }

    @Override
    public void delete(int table_id) {
        orderRepository.delete(table_id);
    }
}
