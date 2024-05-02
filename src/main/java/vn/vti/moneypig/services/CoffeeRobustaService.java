package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.CoffeeRobusta;
import vn.vti.moneypig.models.CoffeePrice;
import vn.vti.moneypig.models.CoffeeProductData;
import vn.vti.moneypig.models.CoffeeRobustaData;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.repositories.CoffeeProductRepository;
import vn.vti.moneypig.repositories.CoffeeRobustaDataRepository;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;
@Service
public class CoffeeRobustaService {


    @Autowired
    CoffeeRobustaDataRepository coffeeRobustaDataRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DataPriceService oilPriceService;
    public CoffeeRobustaData create()
    {
        CoffeeRobustaData coffeeData = new CoffeeRobustaData();
        Long id = sequenceGeneratorService.generateSequence(CoffeeProductData.SEQUENCE_NAME);
        coffeeData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));
        Optional<CoffeeRobustaData> optionalFinancialData = coffeeRobustaDataRepository.findByUtcTime(timeUTCCurrent);
        Optional<Command> lastCommand = commandRepository.findById(5L);
        Command command =  lastCommand.get();
        String timeUTC = command.getTimeUTC().substring(0,16);
        coffeeData.setUtcTime(timeUTCCurrent);
        int value = 0;// tao random
        // Co giá trị -1 0 1
        // Giá trị = 0 có nghĩa là không làm gì
        // Giá trị  = -1 là kéo xuống
        // Giá trị = 1 là kéo lên
        if(timeUTC.equals(timeUTCCurrent))
        {
            value = command.getValue();
            // 0 1 2 | 0 la giam, 1 random 2 tang
        }
        System.out.println("Value:"+ value);
        //==========================================================================
        CoffeeRobusta oilPrice = oilPriceService.getCoffeeRobusta();
        double priceOrigin = Double.parseDouble(oilPrice.getMatchingPrice().replace(',','.'));

        double high = priceOrigin + 2;
        double low = priceOrigin - 2;
        //==========================================================================
        double min1 = priceOrigin;
        double max1 = high;

        Random random = new Random();
        double randomValue = priceOrigin + (max1 - min1) * random.nextDouble();
        double open = randomValue;
        //===========================================================================
        double min2 = low;
        double max2 = priceOrigin;
        double randomValue2 = low + (max2 - min2) * random.nextDouble();
        double close = randomValue2;
        int randInt = random.nextInt(2);
        float randDelta = random.nextFloat(2);
        //============================================================================
        // 0 or 1;
        if(value ==0)
        {
            if(randInt == 0)
            {
                coffeeData.setClose(close-randDelta);
                coffeeData.setHigh(high-randDelta);
                coffeeData.setLow(low-randDelta);
                coffeeData.setOpen(open-randDelta);
                coffeeData.setUtcTime(DateUtils.getCurrentTimeUTC());
                coffeeData.setUp(false);
            }
            else
            {
                coffeeData.setClose(open+randDelta);
                coffeeData.setHigh(high+randDelta);
                coffeeData.setLow(low+randDelta);
                coffeeData.setOpen(close+randDelta);
                coffeeData.setUp(true);
                coffeeData.setUtcTime(DateUtils.getCurrentTimeUTC());
            }
        }else if(value==1) {

            coffeeData.setClose(close+randDelta);
            coffeeData.setHigh(high+randDelta);
            coffeeData.setLow(low+randDelta);
            coffeeData.setOpen(open+randDelta);
            coffeeData.setUtcTime(DateUtils.getCurrentTimeUTC());
            coffeeData.setUp(true);


        }else if(value ==-1){
            coffeeData.setUp(false);

            coffeeData.setClose(open-randDelta);
            coffeeData.setHigh(high-randDelta);
            coffeeData.setLow(low-randDelta);
            coffeeData.setOpen(close-randDelta);
            coffeeData.setUtcTime(DateUtils.getCurrentTimeUTC());
        }

        coffeeData.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        return coffeeRobustaDataRepository.insert(coffeeData);
    }
    public List<CoffeeRobustaData> getLast20Data(){

        return  coffeeRobustaDataRepository.findFirst20ByOrderByIdDesc();
    }

    public List<CoffeeRobustaData> findAll()
    {
        return coffeeRobustaDataRepository.findAll();
    }

    public  boolean deleteAll()
    {
        coffeeRobustaDataRepository.deleteAll();
        return  true;
    }
}
