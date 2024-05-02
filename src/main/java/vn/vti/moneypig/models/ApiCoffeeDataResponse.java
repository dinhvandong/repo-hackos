package vn.vti.moneypig.models;

import java.util.List;

public class ApiCoffeeDataResponse {

    private List<CoffeePrice> data;

    public List<CoffeePrice> getData() {
        return data;
    }

    public void setData(List<CoffeePrice> data) {
        this.data = data;
    }
}
