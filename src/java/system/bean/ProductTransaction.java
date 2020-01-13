/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import system.bean.TransactionType;

/**
 *
 * @author MacMuffin
 */
public class ProductTransaction {
   private int id = -1;
   private TransactionType transactionType;
   private Timestamp timestamp;
   private User user;
   
   public ProductTransaction() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public TransactionType getTransactionType() {
      return transactionType;
   }

   public void setTransactionType(TransactionType transactionType) {
      this.transactionType = transactionType;
   }

   public Timestamp getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
   }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
}
