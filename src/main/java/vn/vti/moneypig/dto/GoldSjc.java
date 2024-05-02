package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoldSjc {
    @JsonProperty("system")
    private String system;
    @JsonProperty("type")
    private String type;
    @JsonProperty("buyingPrice")
    private String buyingPrice;
    @JsonProperty("buyingPriceUp")
    private String buyingPriceUp;
    @JsonProperty("buyingPriceDown")
    private String buyingPriceDown;
    @JsonProperty("buyingPriceNotChange")
    private String buyingPriceNotChange;
    @JsonProperty("sellingPrice")
    private String sellingPrice;
    @JsonProperty("sellingPriceUp")
    private String sellingPriceUp;
    @JsonProperty("sellingPriceDown")
    private String sellingPriceDown;
    @JsonProperty("sellingPriceNotChange")
    private String sellingPriceNotChange;

    // Getters and setters

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getBuyingPriceUp() {
        return buyingPriceUp;
    }

    public void setBuyingPriceUp(String buyingPriceUp) {
        this.buyingPriceUp = buyingPriceUp;
    }

    public String getBuyingPriceDown() {
        return buyingPriceDown;
    }

    public void setBuyingPriceDown(String buyingPriceDown) {
        this.buyingPriceDown = buyingPriceDown;
    }

    public String getBuyingPriceNotChange() {
        return buyingPriceNotChange;
    }

    public void setBuyingPriceNotChange(String buyingPriceNotChange) {
        this.buyingPriceNotChange = buyingPriceNotChange;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSellingPriceUp() {
        return sellingPriceUp;
    }

    public void setSellingPriceUp(String sellingPriceUp) {
        this.sellingPriceUp = sellingPriceUp;
    }

    public String getSellingPriceDown() {
        return sellingPriceDown;
    }

    public void setSellingPriceDown(String sellingPriceDown) {
        this.sellingPriceDown = sellingPriceDown;
    }

    public String getSellingPriceNotChange() {
        return sellingPriceNotChange;
    }

    public void setSellingPriceNotChange(String sellingPriceNotChange) {
        this.sellingPriceNotChange = sellingPriceNotChange;
    }
}
