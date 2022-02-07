package com.pizza.pizzamakerservise.service.impl;

import com.pizza.pizzamakerservise.model.Order;
import com.pizza.pizzamakerservise.model.Product;
import com.pizza.pizzamakerservise.model.dto.OrderDto;
import com.pizza.pizzamakerservise.repository.OrderRepository;
import com.pizza.pizzamakerservise.service.OrderService;
import com.pizza.pizzamakerservise.service.ProductService;

import java.util.LinkedList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository = new OrderRepository();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public OrderDto read(int table_id) {
        OrderDto data = new OrderDto();
        List<Order> fromDb = orderRepository.read(table_id);
        data.setTableId(fromDb.get(0).getTable_id());
        data.setInProcess(fromDb.get(0).isIn_process());
        data.setQuantity(fromDb.get(0).getQuantity());
        data.setProducts(new LinkedList<>());
        int amount = 0;

        for (Order item : fromDb) {
            Product product = productService.readProduct(item.getProductId());
            amount += item.getQuantity() * product.getPrice();
            data.getProducts().add(product);
        }
        data.setAmount(amount);

        return data;
    }



    @Override
    public List<OrderDto> readAll() {
        List<Order> fromDb = orderRepository.readAll();
        List<OrderDto> data = new LinkedList<>();
        fromDb.forEach(item -> {
            int i = 0;
            for (; i < data.size(); i++) {
                if (data.get(i).getTableId() == item.getTable_id()) {
                    break;
                }
            }

            if (i != data.size()) {
                OrderDto orderDto = data.get(i);
                Product product = productService.readProduct(item.getProductId());
                orderDto.getProducts().add(product);
                orderDto.setAmount(orderDto.getAmount() + product.getPrice() * item.getQuantity());


            } else {
                OrderDto orderDto = new OrderDto();
                orderDto.setTableId(item.getTable_id());
                orderDto.setInProcess(item.isIn_process());
                orderDto.setProducts(new LinkedList<>());

                Product product = productService.readProduct(item.getProductId());
                orderDto.getProducts().add(product);
                orderDto.setAmount(item.getQuantity() * product.getPrice());
                data.add(orderDto);

            }

            // checked identifier
            item.setId(-1);
        });


        return data;
    }
    @Override
    public void create(Order order) {
        orderRepository.create(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.update(order);
    }

    @Override
    public void delete(int table_id) {
        orderRepository.delete(table_id);
    }
}
