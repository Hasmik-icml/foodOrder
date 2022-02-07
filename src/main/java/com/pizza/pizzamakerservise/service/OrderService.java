package com.pizza.pizzamakerservise.service;

import com.pizza.pizzamakerservise.model.Order;
import com.pizza.pizzamakerservise.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void create(Order order);

    OrderDto read(int table_id);

    List<OrderDto> readAll();

    Order update(Order order);

    void delete(int table_id);
}
