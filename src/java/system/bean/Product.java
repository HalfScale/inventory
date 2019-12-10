/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

import java.math.BigDecimal;

/**
 *
 * @author MacMuffin
 */
public class Product {
   private int id;
   private Brand brand;
   private Category category;
   private String name;
   private String code;
   private String description;
   private BigDecimal price;
   private String resellerPrice;
   private boolean status;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Brand getBrand() {
      return brand;
   }

   public void setBrand(Brand brand) {
      this.brand = brand;
   }

   public Category getCategory() {
      return category;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getResellerPrice() {
      return resellerPrice;
   }

   public void setResellerPrice(String resellerPrice) {
      this.resellerPrice = resellerPrice;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }
   
   
}
