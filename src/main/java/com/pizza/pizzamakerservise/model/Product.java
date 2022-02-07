package com.pizza.pizzamakerservise.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lombok.*;

import java.util.List;
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

     private float price;

     private String imagePath;

     private String currency;

}
