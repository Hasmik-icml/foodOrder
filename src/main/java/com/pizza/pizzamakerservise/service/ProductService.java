package com.pizza.pizzamakerservise.service;

import com.pizza.pizzamakerservise.model.Product;
import com.pizza.pizzamakerservise.model.Table;

import java.util.List;

public interface ProductService {
    Product read(int id);

    List<Product> readAll();

    void create(Product product);

    Product update (int id, Product product);

    void delete (int id);
}
