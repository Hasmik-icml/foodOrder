package com.pizza.pizzamakerservise.service;

import com.pizza.pizzamakerservise.model.Table;

import java.util.List;

public interface TableService {

    Table read(int id);

    List<Table> readAll();

    void create(Table table);

    Table update (int id, Table table);

    void delete (int id);
}