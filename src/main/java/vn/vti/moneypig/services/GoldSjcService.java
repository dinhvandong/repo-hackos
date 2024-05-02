package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.CoffeeRobusta;
import vn.vti.moneypig.dto.GoldSjc;
import vn.vti.moneypig.models.CoffeeProductData;
import vn.vti.moneypig.models.CoffeeRobustaData;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.GoldSjcData;
import vn.vti.moneypig.repositories.CoffeeRobustaDataRepository;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.GoldSjcDataRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GoldSjcService {



    @Autowired
    GoldSjcDataRepository goldSjcDataRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DataPriceService oilPriceService;
    public GoldSjcData create()
    {
        GoldSjcData goldSjcData = new GoldSjcData();
        Long id = sequenceGeneratorService.generateSequence(GoldSjcData.SEQUENCE_NAME);
        goldSjcData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));
        Optional<GoldSjcData> optionalFinancialData = goldSjcDataRepository.findByUtcTime(timeUTCCurrent);
        Optional<Command> lastCommand = commandRepository.findById(7L);
        Command command =  lastCommand.get();
        String timeUTC = command.getTimeUTC().substring(0,16);
        goldSjcData.setUtcTime(timeUTCCurrent);
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
        GoldSjc goldSjc = oilPriceService.getGoldSjc();
        double priceOrigin = Double.parseDouble(goldSjc.getBuyingPrice().replace(".",""));

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
                goldSjcData.setClose(close-randDelta);
                goldSjcData.setHigh(high-randDelta);
                goldSjcData.setLow(low-randDelta);
                goldSjcData.setOpen(open-randDelta);
                goldSjcData.setUtcTime(DateUtils.getCurrentTimeUTC());
                goldSjcData.setUp(false);
            }
            else
            {
                goldSjcData.setClose(open+randDelta);
                goldSjcData.setHigh(high+randDelta);
                goldSjcData.setLow(low+randDelta);
                goldSjcData.setOpen(close+randDelta);
                goldSjcData.setUp(true);
                goldSjcData.setUtcTime(DateUtils.getCurrentTimeUTC());
            }
        }else if(value==1) {

            goldSjcData.setClose(close+randDelta);
            goldSjcData.setHigh(high+randDelta);
            goldSjcData.setLow(low+randDelta);
            goldSjcData.setOpen(open+randDelta);
            goldSjcData.setUtcTime(DateUtils.getCurrentTimeUTC());
            goldSjcData.setUp(true);


        }else if(value ==-1){
            goldSjcData.setUp(false);

            goldSjcData.setClose(open-randDelta);
            goldSjcData.setHigh(high-randDelta);
            goldSjcData.setLow(low-randDelta);
            goldSjcData.setOpen(close-randDelta);
            goldSjcData.setUtcTime(DateUtils.getCurrentTimeUTC());
        }

        goldSjcData.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        return goldSjcDataRepository.insert(goldSjcData);
    }
    public List<GoldSjcData> getLast20Data(){

        return  goldSjcDataRepository.findFirst20ByOrderByIdDesc();
    }

    public List<GoldSjcData> findAll()
    {
        return goldSjcDataRepository.findAll();
    }

    public  boolean deleteAll()
    {
        goldSjcDataRepository.deleteAll();
        return  true;
    }
}
