package vn.vti.moneypig.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.vti.moneypig.dto.*;
import vn.vti.moneypig.models.*;

@Service
public class DataPriceService {
    private final RestTemplate restTemplate;

    public DataPriceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public OilProduct getOilE5(){
        String apiUrl = "http://150.95.113.18:3000/api/v1/oil-price";
        //150.95.113.18:3000/api/v1/world-oil-prices
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<OildProductData> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, OildProductData.class);
        OildProductData oilPriceData = response.getBody();
        if (oilPriceData != null) {
            return oilPriceData.getData()[0];
        }
        return null;

    }

    public OilProduct getOilRon95V(){
        String apiUrl = "http://150.95.113.18:3000/api/v1/oil-price";
        //150.95.113.18:3000/api/v1/world-oil-prices
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<OildProductData> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, OildProductData.class);
        OildProductData oilPriceData = response.getBody();
        if (oilPriceData != null) {
            return oilPriceData.getData()[5];
        }
        return null;

    }


    public OilPrice getOilPrice2() {
        String apiUrl = "http://150.95.113.18:3000/api/v1/world-oil-prices";
        //150.95.113.18:3000/api/v1/world-oil-prices

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body if required
        // For a POST request, you may need to send a request body depending on the API's requirements
        // Modify the requestBody object with the appropriate data structure and values

        // MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        // requestBody.add("key1", "value1");
        // requestBody.add("key2", "value2");

        // Create the HTTP entity with headers and (optional) request body
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<OilPriceData> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, OilPriceData.class);
        OilPriceData oilPriceData = response.getBody();

        if (oilPriceData != null) {
            return oilPriceData.getData()[0];
        }

        return null;
    }

    public CoffeePrice getCoffeePrice(){

        String apiUrl = "http://150.95.113.18:3000/api/v1/coffe-price";
        //150.95.113.18:3000/api/v1/world-oil-prices

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body if required
        // For a POST request, you may need to send a request body depending on the API's requirements
        // Modify the requestBody object with the appropriate data structure and values

        // MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        // requestBody.add("key1", "value1");
        // requestBody.add("key2", "value2");

        // Create the HTTP entity with headers and (optional) request body
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<ApiCoffeeDataResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ApiCoffeeDataResponse.class);
        ApiCoffeeDataResponse oilPriceData = response.getBody();

        if (oilPriceData != null) {
            return oilPriceData.getData().get(0);
        }

        return null;
    }



    public CoffeeRobusta getCoffeeRobusta(){

        String apiUrl = "http://150.95.113.18:3000/api/v1/coffe-robusta";
        //150.95.113.18:3000/api/v1/world-oil-prices

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body if required
        // For a POST request, you may need to send a request body depending on the API's requirements
        // Modify the requestBody object with the appropriate data structure and values

        // MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        // requestBody.add("key1", "value1");
        // requestBody.add("key2", "value2");

        // Create the HTTP entity with headers and (optional) request body
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<ApiCoffeeRobustaResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ApiCoffeeRobustaResponse.class);
        ApiCoffeeRobustaResponse oilPriceData = response.getBody();

        if (oilPriceData != null) {
            return oilPriceData.getData().get(0);
        }

        return  null;
    }


    public CoffeeArabica getCoffeeArabica(){

        String apiUrl = "http://150.95.113.18:3000/api/v1/coffe-arabica";
        //150.95.113.18:3000/api/v1/world-oil-prices

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body if required
        // For a POST request, you may need to send a request body depending on the API's requirements
        // Modify the requestBody object with the appropriate data structure and values

        // MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        // requestBody.add("key1", "value1");
        // requestBody.add("key2", "value2");

        // Create the HTTP entity with headers and (optional) request body
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<ApiCoffeeArabicaResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ApiCoffeeArabicaResponse.class);
        ApiCoffeeArabicaResponse oilPriceData = response.getBody();

        if (oilPriceData != null) {
            return oilPriceData.getData().get(0);
        }

        return  null;
    }


    public GoldSjc getGoldSjc(){

        String apiUrl = "http://150.95.113.18:3000/api/v1/gold-price-sjc";
        //150.95.113.18:3000/api/v1/world-oil-prices

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body if required
        // For a POST request, you may need to send a request body depending on the API's requirements
        // Modify the requestBody object with the appropriate data structure and values

        // MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        // requestBody.add("key1", "value1");
        // requestBody.add("key2", "value2");

        // Create the HTTP entity with headers and (optional) request body
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<ApiGoldSjcResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ApiGoldSjcResponse.class);
        ApiGoldSjcResponse goldSjcResponse = response.getBody();

        if (goldSjcResponse != null) {
            return goldSjcResponse.getData().get(0);
        }

        return  null;
    }

    public GoldBtmc getGoldBtmc(){

        String apiUrl = "http://150.95.113.18:3000/api/v1/gold-price-btmc";
        //150.95.113.18:3000/api/v1/world-oil-prices

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body if required
        // For a POST request, you may need to send a request body depending on the API's requirements
        // Modify the requestBody object with the appropriate data structure and values

        // MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        // requestBody.add("key1", "value1");
        // requestBody.add("key2", "value2");

        // Create the HTTP entity with headers and (optional) request body
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<ApiGoldBtmcResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ApiGoldBtmcResponse.class);
        ApiGoldBtmcResponse goldSjcResponse = response.getBody();

        if (goldSjcResponse != null) {
            return goldSjcResponse.getData().get(0);
        }

        return  null;
    }
    public OilPrice getOilPrice() {
        String apiUrl = "http://150.95.113.18:3000/api/v1/world-oil-prices";

        ResponseEntity<OilPrice[]> response = restTemplate.getForEntity(apiUrl, OilPrice[].class);
        OilPrice[] oilPrices = response.getBody();

        if (oilPrices != null && oilPrices.length > 0) {
            return oilPrices[0];
        }

        return null;
    }
}