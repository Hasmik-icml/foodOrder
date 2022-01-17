package com.pizza.pizzamakerservise.service;

import com.pizza.pizzamakerservise.model.ProductType;
import com.pizza.pizzamakerservise.model.Table;

import java.util.List;

public interface ProductTypeService {
    ProductType read(int id);

    List<ProductType> readAll();

    void create(ProductType productType);

    Table update (int id, ProductType roductType);

    void delete (int id);
}
