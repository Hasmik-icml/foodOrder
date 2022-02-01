package com.pizza.pizzamakerservise.service.impl;

import com.pizza.pizzamakerservise.model.Ingredient;
import com.pizza.pizzamakerservise.repository.IngredientRepository;
import com.pizza.pizzamakerservise.service.IngredientService;

import java.util.List;

public class IngredientSericeImpl implements IngredientService {
    private final IngredientRepository ingredientRepository = new IngredientRepository();
    @Override
    public Ingredient read(int id) {
        return ingredientRepository.read(id);
    }

    @Override
    public Ingredient read(String name) {
        return ingredientRepository.read(name);
    }

    @Override
    public List<Ingredient> readAll() {
        return ingredientRepository.readAll();
    }

    @Override
    public void create(Ingredient ingredient) {
        if (ingredient==null){
            return;
        }
        ingredient.setId(0);
        ingredientRepository.create(ingredient);
    }

    @Override
    public Ingredient update(int id, Ingredient ingredient) {
        if (ingredientRepository.read(id)!=null){
            ingredient.setId(id);
            return ingredientRepository.update(ingredient);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        ingredientRepository.delete(id);
    }
}
