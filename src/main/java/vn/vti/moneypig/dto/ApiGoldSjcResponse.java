package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiGoldSjcResponse {

    @JsonProperty("data")
    private List<GoldSjc> data;

    public List<GoldSjc> getData() {
        return data;
    }

    public void setData(List<GoldSjc> data) {
        this.data = data;
    }
}
