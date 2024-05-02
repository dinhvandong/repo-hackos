package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoffeeRobusta {
    @JsonProperty("date")
    private String date;
    @JsonProperty("matchingPrice")
    private String matchingPrice;
    @JsonProperty("change")
    private String change;
    @JsonProperty("percent")
    private String percent;
    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("highest")
    private String highest;
    @JsonProperty("lowest")
    private String lowest;
    @JsonProperty("open")
    private String open;
    @JsonProperty("yesterday")
    private String yesterday;
    @JsonProperty("openContract")
    private String openContract;

    // Getters and setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatchingPrice() {
        return matchingPrice;
    }

    public void setMatchingPrice(String matchingPrice) {
        this.matchingPrice = matchingPrice;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getYesterday() {
        return yesterday;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }

    public String getOpenContract() {
        return openContract;
    }

    public void setOpenContract(String openContract) {
        this.openContract = openContract;
    }
}
