package com.pizza.pizzamakerservise.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Product {
     private int id;
     private int productTypeId;
     private String name;
     private Float price;
     private String img;

     public Product() {
     }

     public Product(int id, int productTypeId, String name, Float price, String img, List<Ingredient> ingredientList) {
          this.id = id;
          this.productTypeId = productTypeId;
          this.name = name;
          this.price = price;
          this.img = img;
          this.ingredientList = ingredientList;
     }


     List<Ingredient> ingredientList;

     public void data(){
          Ingredient ingredient = new Ingredient();
          ingredient.getId();
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public int getProductTypeId() {
          return productTypeId;
     }

     public void setProductTypeId(int productTypeId) {
          this.productTypeId = productTypeId;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public Float getPrice() {
          return price;
     }

     public void setPrice(Float price) {
          this.price = price;
     }

     public String getImg() {
          return img;
     }

     public void setImg(String img) {
          this.img = img;
     }

     public List<Ingredient> getIngredientList() {
          return ingredientList;
     }

     public void setIngredientList(List<Ingredient> ingredientList) {
          this.ingredientList = ingredientList;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Product product = (Product) o;
          return id == product.id && productTypeId == product.productTypeId && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(img, product.img) && Objects.equals(ingredientList, product.ingredientList);
     }

     @Override
     public int hashCode() {
          return Objects.hash(id, productTypeId, name, price, img, ingredientList);
     }

     @Override
     public String toString() {
          return "Product{" +
                  "id=" + id +
                  ", productTypeId=" + productTypeId +
                  ", name='" + name + '\'' +
                  ", price=" + price +
                  ", img='" + img + '\'' +
                  ", ingredientList=" + ingredientList +
                  '}';
     }
}
