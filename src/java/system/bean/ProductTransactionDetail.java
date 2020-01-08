/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

import system.bean.Product;

/**
 *
 * @author MacMuffin
 */
public class ProductTransactionDetail {
   private int id = -1;
   private ProductTransaction productTransaction;
   private Product product;
   private int quantity;
   private boolean isReseller;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public ProductTransaction getProductTransaction() {
      return productTransaction;
   }

   public void setProductTransaction(ProductTransaction productTransaction) {
      this.productTransaction = productTransaction;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public boolean isIsReseller() {
      return isReseller;
   }

   public void setIsReseller(boolean isReseller) {
      this.isReseller = isReseller;
   }
   
}
