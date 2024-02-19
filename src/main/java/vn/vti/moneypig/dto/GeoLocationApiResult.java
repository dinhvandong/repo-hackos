package vn.vti.moneypig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoLocationApiResult {

    private String country;

    private String city;

    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("lon")
    private String longitude;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
// Getters and setters
}
