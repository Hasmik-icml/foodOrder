package com.pizza.pizzamakerservise;

import com.google.gson.Gson;
import com.pizza.pizzamakerservise.repository.OrderRepository;
import com.pizza.pizzamakerservise.util.SQLConnecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
//        Connection connection = SQLConnecter.getConnection();
//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement("insert into `table` values (0,1,1,true)");
//            int count = preparedStatement.executeUpdate();
//        }catch(SQLException exception){
//            exception.printStackTrace();
//        }
        OrderRepository orderRepository = new OrderRepository();
        Gson gson = new Gson();
        System.out.println(gson.toJson(orderRepository.readAll()));

    }
}
