package com.pizza.pizzamakerservise.service;

import com.pizza.pizzamakerservise.model.Order;
import com.pizza.pizzamakerservise.model.Table;

import java.util.List;

public interface OrderService {

    Order read(int table_id);

    List<Order> readAll();

    void create(Order order);

    Order update(int table_id, Order order);

    void delete(int table_id);
}
