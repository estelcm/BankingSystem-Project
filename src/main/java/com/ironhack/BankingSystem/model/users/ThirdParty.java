package com.ironhack.BankingSystem.model.users;


import jakarta.persistence.Entity;

@Entity
class ThirdParty extends User{

   private String hashedKey;

   public ThirdParty() {
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
