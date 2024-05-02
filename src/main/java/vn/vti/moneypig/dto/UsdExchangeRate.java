package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsdExchangeRate {

    private String name;
    private String key;
    private String buyingPrice;
    private String buyingPriceUp;
    private String buyingPriceDown;
    private String buyingPriceNotChange;
    private String sellingPrice;
    private String sellingPriceUp;
    private String sellingPriceDown;
    private String sellingPriceNotChange;
    private String transferPrice;
    private String transferPriceUp;
    private String transferPriceDown;
    private String transferPriceNotChange;
    private String currencyName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(String transferPrice) {
        this.transferPrice = transferPrice;
    }

    public String getTransferPriceUp() {
        return transferPriceUp;
    }

    public void setTransferPriceUp(String transferPriceUp) {
        this.transferPriceUp = transferPriceUp;
    }

    public String getTransferPriceDown() {
        return transferPriceDown;
    }

    public void setTransferPriceDown(String transferPriceDown) {
        this.transferPriceDown = transferPriceDown;
    }

    public String getTransferPriceNotChange() {
        return transferPriceNotChange;
    }

    public void setTransferPriceNotChange(String transferPriceNotChange) {
        this.transferPriceNotChange = transferPriceNotChange;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
