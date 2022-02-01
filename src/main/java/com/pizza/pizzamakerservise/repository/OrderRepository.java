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

public class OrderRepository {
    public Order read(int table_id) {
        Connection connection = SQLConnecter.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = connection.prepareStatement("SELECT * from `order` WHERE table_id=?");
            pstmt.setInt(1, table_id);
            resultSet = pstmt.executeQuery();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        Order order=null;
        try{
            while (resultSet.next()){
                order=mapper(resultSet);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        try{
            pstmt.close();
            resultSet.close();
            connection.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return order;

    }

    public List<Order> readAll() {

        Connection connection = SQLConnecter.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement("SELECT * from `order`");
            resultSet = pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        List<Order> data = mapperList(resultSet);


        try {
            pstmt.close();
            resultSet.close();
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

    public Order update(int id, Order order) { /// Id or table_id
        Connection connection = SQLConnecter.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `order` SET table_id = ?, in_process = ?, productId=?, quantity = ?, amount = ? WHERE id = ?");
            preparedStatement.setInt(1, order.getTable_id());
            preparedStatement.setBoolean(2, order.isIn_process());
            preparedStatement.setInt(3, order.getProductId());
            preparedStatement.setInt(4, order.getQuantity());
            preparedStatement.setFloat(5, order.getAmount());
            preparedStatement.setInt(6, order.getId());

            int i = preparedStatement.executeUpdate();//???????????????

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

            int i = preparedStatement.executeUpdate();//????????????????

            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    private static List<Order> mapperList(ResultSet resultSet) {
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

    private static Order mapper(ResultSet resultSet) {
        Order o = new Order();
        try {
            o.setId(resultSet.getInt("id"));
            o.setTable_id(resultSet.getInt("table_id"));
            o.setIn_process(resultSet.getBoolean("in_process"));
            o.setProductId(resultSet.getInt("productId"));
            o.setQuantity(resultSet.getInt("quantity"));
            o.setAmount(resultSet.getFloat("amount"));

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return o;
    }

}
