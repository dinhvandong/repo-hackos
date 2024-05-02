package vn.vti.moneypig.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.vti.moneypig.services.*;

@Component
@EnableScheduling
public class CountdownTimer {

    private int remainingSeconds = 60;

    @Autowired
    OilWorldDataService oilWorldDataService;

    @Autowired
    OilProductDataService oilProductDataService;

    @Autowired
    CoffeeProductService coffeeProductService;


    @Autowired
    CoffeeRobustaService coffeeRobustaService;
    @Autowired
    OilProductRon95DataService oilProductRon95DataService;

    @Autowired
    CoffeeArabicaService coffeeArabicaService;

    @Autowired
    GoldSjcService goldSjcService;

    @Autowired
    GoldBtmcService goldBtmcService;

//    @Scheduled(fixedRate = 60000) // Run every minute

    @Scheduled(fixedRate = 1000) // Run every second
    public void runCountdown() {
        if (remainingSeconds > 0) {
            System.out.println("Countdown: " + remainingSeconds + " seconds");
            remainingSeconds--;
        } else {

            remainingSeconds=60;
            oilWorldDataService.create();
            oilProductDataService.create();
            oilProductRon95DataService.create();
            coffeeProductService.create();
            coffeeRobustaService.create();
            coffeeArabicaService.create();
            goldSjcService.create();
            goldBtmcService.create();

            System.out.println("Countdown finished!");
        }
    }
}