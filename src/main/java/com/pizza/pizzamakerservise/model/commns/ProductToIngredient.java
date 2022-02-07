package com.pizza.pizzamakerservise.model.commns;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductToIngredient {
    private int id;

    private int ingredientId;

    private int productId;
}
