package vn.vti.moneypig.models;

public class OilPrice {
    private String oil;
    private String price;
    private String increaseAndDecrease;
    private String percent;

    public  OilPrice()
    {

    }

    public OilPrice(String oil, String price, String increaseAndDecrease, String percent) {
        this.oil = oil;
        this.price = price;
        this.increaseAndDecrease = increaseAndDecrease;
        this.percent = percent;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIncreaseAndDecrease() {
        return increaseAndDecrease;
    }

    public void setIncreaseAndDecrease(String increaseAndDecrease) {
        this.increaseAndDecrease = increaseAndDecrease;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    // Generate getters and setters
    // ...
}