package vn.vti.moneypig.dto;

import vn.vti.moneypig.models.Gold;

import java.util.List;

public class GoldResponse {

    private String address;

    private List<Gold> goldList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Gold> getGoldList() {
        return goldList;
    }

    public void setGoldList(List<Gold> goldList) {
        this.goldList = goldList;
    }
}
