package com.ironhack.BankingSystem.model.users;


import jakarta.persistence.Entity;

@Entity
public class ThirdParty extends User{

   private String hashedKey;

   public ThirdParty() {
   }

   public ThirdParty(String name, String hashedKey) {
      super(name, name,"eepa");
      this.hashedKey = hashedKey;
   }

   public ThirdParty(String hashedKey) {
      this.hashedKey = hashedKey;
   }

   public String getHashedKey() {
      return hashedKey;
   }

   public void setHashedKey(String hashedKey) {
      this.hashedKey = hashedKey;
   }
}
