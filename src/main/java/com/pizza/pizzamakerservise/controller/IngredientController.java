package com.pizza.pizzamakerservise.controller;

import com.google.gson.Gson;
import com.pizza.pizzamakerservise.model.Ingredient;
import com.pizza.pizzamakerservise.model.Table;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class IngredientController extends HttpServlet {

    private List<Ingredient> list = new LinkedList<>();
    private static Random random = new Random();

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("This is Get method in Ingredient");
        if (list.size() == 0) {
            for (int i = 0; i < 10; i++) {
                list.add(new Ingredient(i, "ingredient" + i + 1, random.nextBoolean(),
                        random.nextInt(10) + 1, random.nextInt(5) + 1,
                        random.nextInt(50) + 1, "gr"));
            }
            System.out.println(list);
        }

        resp.getWriter().println(gson.toJson(list));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is post method in Ingredient");

        String name = req.getParameter("name");
        boolean editable = Boolean.parseBoolean(req.getParameter("editable"));
        int editStep = Integer.parseInt(req.getParameter("editStep"));
        int minBound = Integer.parseInt(req.getParameter("minBound"));
        int maxBound = Integer.parseInt(req.getParameter("maxBound"));
        String measurement = req.getParameter("measurement");


        int id = list.get(list.size() - 1).getId() + 1;

        Ingredient data = new Ingredient(id, name, editable, editStep,minBound,maxBound,measurement);

        list.add(data);


        resp.getWriter().println(gson.toJson(list));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is PUT method in ingredient ");

        Ingredient ing = null;
        int id = Integer.parseInt(req.getParameter("id"));

        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getId() == id){
                ing = list.get(i);
                break;
            }
        }
        if (ing == null){
            resp.sendError(400, "not found");
            return;
        }

        String name = req.getParameter("name");
        boolean editable = Boolean.parseBoolean(req.getParameter("editable"));
        int editStep = Integer.parseInt(req.getParameter("editStep"));
        int minBound = Integer.parseInt(req.getParameter("minBound"));
        int maxBound = Integer.parseInt(req.getParameter("maxBound"));
        String measurement = req.getParameter("measurement");

        ing.setName(name);
        ing.setEditable(editable);
        ing.setEditStep(editStep);
        ing.setMinBound(minBound);
        ing.setMaxBound(maxBound);
        ing.setMeasurement(measurement);

        resp.getWriter().println(gson.toJson(list));
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("this is DELETE method in ingredient");

        int delId =  Integer.parseInt(req.getParameter("id"));

        List<Ingredient> collect = list.stream().filter(item -> item.getId() == delId ).collect(Collectors.toList());

        list.removeAll(collect);

        resp.getWriter().println(gson.toJson(list));
    }
}
