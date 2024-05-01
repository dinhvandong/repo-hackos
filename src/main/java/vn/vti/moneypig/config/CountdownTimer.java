package vn.vti.moneypig.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.vti.moneypig.services.OilProductDataService;
import vn.vti.moneypig.services.OilWorldDataService;

@Component
@EnableScheduling
public class CountdownTimer {

    private int remainingSeconds = 60;

    @Autowired
    OilWorldDataService oilWorldDataService;

    @Autowired
    OilProductDataService oilProductDataService;



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
            System.out.println("Countdown finished!");
        }
    }
}