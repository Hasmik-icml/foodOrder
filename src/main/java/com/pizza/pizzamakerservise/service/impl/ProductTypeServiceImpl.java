package com.pizza.pizzamakerservise.service.impl;

import com.pizza.pizzamakerservise.model.ProductType;
import com.pizza.pizzamakerservise.model.Table;
import com.pizza.pizzamakerservise.repository.ProductTypeRepository;
import com.pizza.pizzamakerservise.service.ProductTypeService;

import java.util.List;

public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository =new ProductTypeRepository();

    @Override
    public ProductType read(int id) {
        return productTypeRepository.read(id);
    }

    @Override
    public List<ProductType> readAll() {
        return productTypeRepository.readAll();
    }


    @Override
    public void create(ProductType productType) {

    }

    @Override
    public ProductType update(int id, ProductType table) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ProductType read(String name) {
        return productTypeRepository.read(name);
    }
}
