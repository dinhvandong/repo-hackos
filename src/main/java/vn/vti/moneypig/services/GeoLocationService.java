//package vn.vti.moneypig.services;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import vn.vti.moneypig.dto.GeoLocationApiResult;
//import vn.vti.moneypig.dto.GeoLocationInfo;
//
//@Service
//public class GeoLocationService {
//
//    @Value("${geolocation.api.url}")
//    private String geolocationApiUrl;
//
//    public GeoLocationInfo getGeoLocationInfo(String ipAddress) {
//        RestTemplate restTemplate = new RestTemplate();
//        String apiUrl = geolocationApiUrl + ipAddress;
//
//        GeoLocationApiResult result = restTemplate.getForObject(apiUrl, GeoLocationApiResult.class);
//
//        if (result != null) {
//            String country = result.getCountry();
//            String city = result.getCity();
//            String latitude = result.getLatitude();
//            String longitude = result.getLongitude();
//
//            return new GeoLocationInfo(country, city, latitude, longitude);
//        }
//
//        return null;
//    }
//}
