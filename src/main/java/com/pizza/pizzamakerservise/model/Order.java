package com.pizza.pizzamakerservise.model;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private int id;
    private int table_id;
    private boolean in_process;
    private int productId;
    private int quantity;
    private float amount;
}
