package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiEuroExchangeRateResponse {

    @JsonProperty("data")
    private List<EuroExchangeRate> data;

    public List<EuroExchangeRate> getData() {
        return data;
    }

    public void setData(List<EuroExchangeRate> data) {
        this.data = data;
    }
}
