package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiGbpExchangeRateResponse {

    @JsonProperty("data")
    private List<GbpExchangeRate> data;

    public List<GbpExchangeRate> getData() {
        return data;
    }

    public void setData(List<GbpExchangeRate> data) {
        this.data = data;
    }
}
