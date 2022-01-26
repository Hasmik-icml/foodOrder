package com.pizza.pizzamakerservise.service.impl;

import com.pizza.pizzamakerservise.model.Table;
import com.pizza.pizzamakerservise.repository.TableRepository;
import com.pizza.pizzamakerservise.service.TableService;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TableServiceImpl implements TableService {
public final TableRepository tableRepository = new TableRepository();

    @Override
    public Table read(int id) {
        return tableRepository.read(id);
    }

    @Override
    public List<Table> readAll() {
       return tableRepository.readAll();
    }

    @Override
    public List<Table> readByBusy(boolean isBusy) {
        return readAll().stream().filter(item -> item.isBusy() == isBusy).collect(Collectors.toList());
    }

    @Override
    public void create(Table table) {

    }

    @Override
    public Table update(int id, Table table) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
