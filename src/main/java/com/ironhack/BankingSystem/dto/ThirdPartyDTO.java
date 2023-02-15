package com.ironhack.BankingSystem.dto;

public class ThirdPartyDTO {


    private String newThirdPartyName;
    private String newHashedKey;

    //private String password;


    public ThirdPartyDTO(String newThirdPartyName, String newHashedKey) {
        this.newThirdPartyName = newThirdPartyName;
        this.newHashedKey = newHashedKey;
    }

    public String getNewThirdPartyName() {
        return newThirdPartyName;
    }

    public void setNewThirdPartyName(String newThirdPartyName) {
        this.newThirdPartyName = newThirdPartyName;
    }

    public String getNewHashedKey() {
        return newHashedKey;
    }

    public void setNewHashedKey(String newHashedKey) {
        this.newHashedKey = newHashedKey;
    }
}
