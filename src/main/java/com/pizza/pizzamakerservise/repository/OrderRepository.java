package com.pizza.pizzamakerservise.repository;

import com.pizza.pizzamakerservise.model.Order;
import com.pizza.pizzamakerservise.model.Table;
import com.pizza.pizzamakerservise.util.SQLConnecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {
    public List<Order> read(int table_id) {
        return readAll().stream().filter(item -> item.getTable_id() == table_id).collect(Collectors.toList());
    }

    public List<Order> readAll() {
        Connection connection = SQLConnecter.getConnection();
        List<Order> data = new LinkedList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from `order`");
            ResultSet resultSet = preparedStatement.executeQuery();
            data.addAll(mapperList(resultSet));

            preparedStatement.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return data;
    }

    public void create(Order order) {
        Connection connection = SQLConnecter.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `order` values (0,?,?,?,?,?)");
            preparedStatement.setInt(1, order.getTable_id());
            preparedStatement.setBoolean(2, order.isIn_process());
            preparedStatement.setInt(3, order.getProductId());
            preparedStatement.setInt(4, order.getQuantity());
            preparedStatement.setFloat(5, order.getAmount());

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Order update(Order order) {
        Connection connection = SQLConnecter.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `order` SET table_id =?, in_process = ?, productId=?, quantity = ?, amount = ? WHERE id = ?");
            preparedStatement.setInt(1, order.getTable_id());
            preparedStatement.setBoolean(2, order.isIn_process());
            preparedStatement.setInt(3, order.getProductId());
            preparedStatement.setInt(4, order.getQuantity());
            preparedStatement.setFloat(5, order.getAmount());
            preparedStatement.setInt(6, order.getId());

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return order;
    }

    public void delete(int table_id) {

        Connection connection = SQLConnecter.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `order` where table_id=?");
            preparedStatement.setInt(1, table_id);

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    private  List<Order> mapperList(ResultSet resultSet) {
        List<Order> data = new LinkedList<>();
        try {
            while (resultSet.next()) {
                data.add(mapper(resultSet));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return data;
    }

    private Order mapper(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int table_id = resultSet.getInt("table_id");
        boolean in_process = resultSet.getBoolean("in_process");
        int product_id = resultSet.getInt("product_id");
        int quantity = resultSet.getInt("quantity");
        float amount = resultSet.getFloat("amount");

        return new Order(id, table_id, in_process, product_id, quantity, amount);

    }

}
