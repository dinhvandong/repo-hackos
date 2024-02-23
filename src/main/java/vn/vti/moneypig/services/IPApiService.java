package vn.vti.moneypig.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.vti.moneypig.dto.AddressInfo;
import vn.vti.moneypig.dto.IPApiResponse;

@Service
public class IPApiService {
    private static final String API_BASE_URL = "http://ip-api.com/json/";
    public AddressInfo getAddressInfo(String ipAddress) {
        String apiUrl = API_BASE_URL + ipAddress;
        RestTemplate restTemplate = new RestTemplate();
        IPApiResponse response = restTemplate.getForObject(apiUrl, IPApiResponse.class);

        if (response != null && response.getStatus().equals("success")) {
            String country = response.getCountry();
            String city = response.getCity();
            String region = response.getRegionName();
            String zipCode = response.getZip();
            String address = response.getQuery();

            return new AddressInfo(country, city, region, zipCode, address);
        } else {
            return null; // or throw an exception
        }
    }
}
