package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiGoldBtmcResponse {

    @JsonProperty("data")
    private List<GoldBtmc> data;

    public List<GoldBtmc> getData() {
        return data;
    }

    public void setData(List<GoldBtmc> data) {
        this.data = data;
    }
}
