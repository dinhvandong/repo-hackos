package vn.vti.moneypig.dto;

public class AddressInfo {
    private String country;
    private String city;
    private String region;
    private String zipCode;
    private String address;

    public AddressInfo(String country, String city, String region, String zipCode, String address) {
        this.country = country;
        this.city = city;
        this.region = region;
        this.zipCode = zipCode;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
// Getters and setters (or accessors and mutators) for the class variables
}