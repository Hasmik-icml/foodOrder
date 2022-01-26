package com.pizza.pizzamakerservise.controller;

import com.google.gson.Gson;
import com.pizza.pizzamakerservise.model.ProductType;
import com.pizza.pizzamakerservise.model.Table;
import com.pizza.pizzamakerservise.service.ProductTypeService;
import com.pizza.pizzamakerservise.service.impl.ProductTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductTypeController extends HttpServlet {
    private final ProductTypeService productTypeService = new ProductTypeServiceImpl();

    public List<ProductType> list = new LinkedList<>();
    private static Random random = new Random();

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is get method in ProductType");
        List<ProductType> data = new LinkedList<>();
        final String url = req.getParameter("url");

        switch (url) {
            case "get-all":
                data = productTypeService.readAll();
                break;
            case "get-by-id":
                int id = Integer.parseInt(req.getParameter("id"));
                data.add(productTypeService.read(id));
                break;
            case "get-by-name":
                String name = req.getParameter("name");
                data.add(productTypeService.read(name));
                break;
            default:
                resp.sendError(404, "provided URL not found");
                break;
        }

        resp.getWriter().println(gson.toJson(data));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is post method in ProductType");

        int id =list.get(list.size()-1).getId() + 1;

        String name = req.getParameter("name");
        ProductType data = new ProductType(id, name);
        list.add(data);
        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.getWriter().println("this is put method in ProductType ");

        ProductType p = null;

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

        String name = req.getParameter("name");

        p.setName(name);
        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is method in ProductType");

        int id = Integer.parseInt(req.getParameter("id"));
        List<ProductType> collect = list.stream().filter(item -> item.getId() == id).collect(Collectors.toList());

        list.removeAll(collect);

        resp.getWriter().println(gson.toJson(list));

    }
}
