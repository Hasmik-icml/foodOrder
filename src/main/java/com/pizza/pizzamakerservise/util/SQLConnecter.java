package com.pizza.pizzamakerservise.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnecter {
    private static final String user="root";
    private static final String password="";
    private static final String DB="foodOrder";
    private static final String url="jdbc:mysql://localhost:3306/"+DB;

    private static Connection connection=null;

    private SQLConnecter() {
    }

    public static Connection getConnection(){
        try {
            if(connection==null || connection.isClosed()){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection= DriverManager.getConnection(url,user,password);
            }
        } catch (SQLException | ClassNotFoundException e  ) {
            e.printStackTrace();
        }
        return connection;
    }
}
