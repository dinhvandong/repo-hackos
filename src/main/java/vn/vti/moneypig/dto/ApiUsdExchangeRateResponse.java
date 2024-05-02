package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiUsdExchangeRateResponse {

    @JsonProperty("data")
    private List<UsdExchangeRate> data;

    public List<UsdExchangeRate> getData() {
        return data;
    }

    public void setData(List<UsdExchangeRate> data) {
        this.data = data;
    }
}
