package com.pizza.pizzamakerservise.controller;

import com.google.gson.Gson;
import com.pizza.pizzamakerservise.model.Order;
import com.pizza.pizzamakerservise.model.Table;
import com.pizza.pizzamakerservise.service.OrderService;
import com.pizza.pizzamakerservise.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class OrderController extends HttpServlet {

     private final OrderService orderService = new OrderServiceImpl();
     private final Gson gson = new Gson();

     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          List<Order> data = new LinkedList<>();

          final String url = req.getParameter("url");

          switch (url) {
               case "get-all":
                    data = orderService.readAll();// վերադարձնում է զանգված
                    break;
               case "get-by-id":
                    int id = Integer.parseInt(req.getParameter("table_id"));
                    Order read = orderService.read(id); // վերադարձնում է օբյեկտ
                    if ( read != null){
                         data.add(read);
                    }
               break;
               default:
                    resp.sendError(404, "provided URL not found for analyse");
                    break;
          }
          resp.getWriter().println(gson.toJson(data));
     }

     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          Order order = mapper(req);
          orderService.create(order);
     }

     @Override
     protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          Order order = mapper(req);
          int table_id = order.getTable_id();
          resp.
                  getWriter().
                  println(gson.toJson(orderService.update(table_id, order)));
     }

     @Override
     protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          int table_id = Integer.parseInt(req.getParameter("table_id"));
          orderService.delete(table_id);
     }


     private Order mapper(HttpServletRequest req) {
          int id;
          int table_id;
          boolean in_process;
          int productId;
          int quantity;
          float amount;

          try {
               id = Integer.parseInt(req.getParameter("id"));
          } catch (NumberFormatException ex) {
               id = 0;
          }
          try {
               table_id = Integer.parseInt(req.getParameter("table_id"));
          } catch (NumberFormatException ex) {
               table_id = 0;
          }
          try {
               productId = Integer.parseInt(req.getParameter("productId"));
          } catch (NumberFormatException ex) {
               productId = 0;
          }
          try {
               quantity = Integer.parseInt(req.getParameter("quantity"));
          } catch (NumberFormatException ex) {
               quantity = 0;
          }
          try {
               amount = Float.parseFloat(req.getParameter("amount"));
          } catch (NumberFormatException ex) {
               amount = 0;
          }
          in_process = Boolean.parseBoolean(req.getParameter("in_process"));

          Order order = new Order(id, table_id, in_process, productId, quantity,amount);
          return order;
     }
}
