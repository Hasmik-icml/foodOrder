package com.pizza.pizzamakerservise.repository;

import com.pizza.pizzamakerservise.model.ProductType;
import com.pizza.pizzamakerservise.util.SQLConnecter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class ProductTypeRepository {

    public ProductType read(int id) {
        Connection connection = SQLConnecter.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM `productType` WHERE id=?");
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        ProductType productType=null;
        try{
            while (resultSet.next()){
                productType=mapper(resultSet);
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
        return productType;
    }

    public ProductType read(String name) {
        Connection connection = SQLConnecter.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM `productType` WHERE name=?");
            pstmt.setString(1,name);
            resultSet = pstmt.executeQuery();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        ProductType productType=null;
        try{
            while (resultSet.next()){
                productType=mapper(resultSet);
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
        return productType;
    }

    public List<ProductType> readAll() {
        Connection connection = SQLConnecter.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement("SELECT * from `product_Type`");
            resultSet = pstmt.executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        List<ProductType> data = mapperList(resultSet);


        try {

            resultSet.close();
            pstmt.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return data;
    }

    public void create(ProductType productType) {
        Connection connection = SQLConnecter.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `productType` values (0,?)");

            preparedStatement.setString(1, productType.getName());


            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public ProductType update(int id, ProductType productType) {

        Connection connection = SQLConnecter.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `productType` SET name = ? WHERE id = ?");
            preparedStatement.setString(1,productType.getName());
            preparedStatement.setInt(2,productType.getId());

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

        return productType;
    }

    public void delete(int id) {
        Connection connection = SQLConnecter.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `productType` where id=?");
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static List<ProductType> mapperList(ResultSet resultSet) {
        List<ProductType> data = new LinkedList<>();
        try {
            while (resultSet.next()) {
                data.add(mapper(resultSet));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return data;
    }

    private static ProductType mapper(ResultSet resultSet) throws SQLException {
        ProductType productType = new ProductType();
        productType.setId(resultSet.getInt("id"));
        productType.setName(resultSet.getString("name"));
        return productType;
    }
}
