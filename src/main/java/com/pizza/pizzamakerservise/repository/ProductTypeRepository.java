package com.pizza.pizzamakerservise.repository;

import com.pizza.pizzamakerservise.model.ProductType;
import com.pizza.pizzamakerservise.model.Table;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ProductTypeRepository {

    public ProductType read(String name){
        return new ProductType(4, name);
    }

    public ProductType read(int id){
        return new ProductType(id, "name" + new Random().nextInt(10)+1);
    }

    public List<ProductType> readAll() {
        List<ProductType> objects = new LinkedList<>();
        objects.add(new ProductType(1, "xorovac"));
        objects.add(new ProductType(2, "alcohol"));
        objects.add(new ProductType(3, "qaxcr"));
        objects.add(new ProductType(4, "sare"));

        return objects;
    }
    public void create (ProductType productType){

    }
    public ProductType update(int id, ProductType productType){
        return null;
    }
    public void delete(int id){

    }
}
