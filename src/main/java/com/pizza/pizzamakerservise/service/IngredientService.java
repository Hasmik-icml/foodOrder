package com.pizza.pizzamakerservise.service;

import com.pizza.pizzamakerservise.model.Ingredient;
import com.pizza.pizzamakerservise.model.Table;

import java.util.List;

public interface IngredientService {
    Ingredient read(int id);

    List<Ingredient> readAll();

    void create(Ingredient ingredient);

    Ingredient update (int id, Ingredient ingredient);

    void delete (int id);
}
