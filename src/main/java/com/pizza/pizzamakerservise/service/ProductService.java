package com.pizza.pizzamakerservise.service;

import com.pizza.pizzamakerservise.model.Product;
import com.pizza.pizzamakerservise.model.Table;
import com.pizza.pizzamakerservise.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    Product readProduct(int id);
    ProductDto read(int id);
    List<ProductDto> readAll();
    List<ProductDto> readAllByProductType(int productTypeId);
    void create(ProductDto productDto);
    ProductDto update(int id, ProductDto productDto);
    void delete(int id);
}
