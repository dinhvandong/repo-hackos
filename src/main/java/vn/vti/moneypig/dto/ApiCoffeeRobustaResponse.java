package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiCoffeeRobustaResponse {

    @JsonProperty("data")
    private List<CoffeeRobusta> data;

    public List<CoffeeRobusta> getData() {
        return data;
    }

    public void setData(List<CoffeeRobusta> data) {
        this.data = data;
    }
}
