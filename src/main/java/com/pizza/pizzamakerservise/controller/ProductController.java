package com.pizza.pizzamakerservise.controller;

import com.google.gson.Gson;
import com.pizza.pizzamakerservise.model.Ingredient;
import com.pizza.pizzamakerservise.model.Product;
import com.pizza.pizzamakerservise.model.ProductType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class ProductController extends HttpServlet {
    private List<Product> list = new LinkedList<>();
    private static Random random = new Random();

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is get method in Product");

        if (list.size()== 0) {
            for (int i = 0; i < 10; i++) {
                list.add(new Product(i, i + random.nextInt(10)+1, "product" + random.nextInt(10) + 1, random.nextFloat() + 100.5f, "img" + random.nextInt(10) + 1));
            }
            System.out.println(list);
        }
        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is post method in Product");

        int id =list.get(list.size()-1).getId() + 1;

        int productTypeId = Integer.parseInt(req.getParameter("productTypeId"));
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        String img = req.getParameter("img");

        Product data = new Product(id, productTypeId,name,price,img);
        list.add(data);
        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is put method in Product ");

        Product p = null;

        int id = Integer.parseInt(req.getParameter("id"));

        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getId() == id){
                p = list.get(i);
                break;
            }
        }
        if (p == null){
            resp.sendError(404,"not found");
            return;
        }

        int productTypeId = Integer.parseInt(req.getParameter("productTypeId"));
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        String img = req.getParameter("img");

        p.setProductTypeId(productTypeId);
        p.setName(name);
        p.setPrice(price);
        p.setImg(img);

        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is method in Product");

        int id = Integer.parseInt(req.getParameter("id"));
        List<Product> collect = list.stream().filter(item -> item.getId() == id).collect(Collectors.toList());

        list.removeAll(collect);

        resp.getWriter().println(gson.toJson(list));

    }
}
