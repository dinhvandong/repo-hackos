package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiCoffeeArabicaResponse {

    @JsonProperty("data")
    private List<CoffeeArabica> data;

    public List<CoffeeArabica> getData() {
        return data;
    }

    public void setData(List<CoffeeArabica> data) {
        this.data = data;
    }
}
