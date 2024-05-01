package vn.vti.moneypig.models;

public class OilProduct {
    private String oilProduct;
    private String priceZone_1;
    private String priceZone_2;

    // Constructors, getters, and setters
    public OilProduct() {
    }

    public OilProduct(String oilProduct, String priceZone_1, String priceZone_2) {
        this.oilProduct = oilProduct;
        this.priceZone_1 = priceZone_1;
        this.priceZone_2 = priceZone_2;
    }

    public String getOilProduct() {
        return oilProduct;
    }

    public void setOilProduct(String oilProduct) {
        this.oilProduct = oilProduct;
    }

    public String getPriceZone_1() {
        return priceZone_1;
    }

    public void setPriceZone_1(String priceZone_1) {
        this.priceZone_1 = priceZone_1;
    }

    public String getPriceZone_2() {
        return priceZone_2;
    }

    public void setPriceZone_2(String priceZone_2) {
        this.priceZone_2 = priceZone_2;
    }
}