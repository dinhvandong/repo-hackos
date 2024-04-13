package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "currency")
public class Currency {
    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "currency_sequence";
    private String code;
    private double priceSell;
    private double priceBuy;
    private double priceTransfer;
    private Long date;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(double priceSell) {
        this.priceSell = priceSell;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public double getPriceTransfer() {
        return priceTransfer;
    }

    public void setPriceTransfer(double priceTransfer) {
        this.priceTransfer = priceTransfer;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
