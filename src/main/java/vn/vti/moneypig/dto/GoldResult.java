package vn.vti.moneypig.dto;

import java.util.List;

public class GoldResult {

    private Long date;

    private List<GoldResponse> goldResponseList;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<GoldResponse> getGoldResponseList() {
        return goldResponseList;
    }

    public void setGoldResponseList(List<GoldResponse> goldResponseList) {
        this.goldResponseList = goldResponseList;
    }
}
