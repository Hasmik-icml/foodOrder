package com.pizza.pizzamakerservise.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Product {
     private int id;
     private int productTypeId;
     private String name;
     private Float price;
     private String img;


}
