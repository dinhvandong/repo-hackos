package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.GoldBtmc;
import vn.vti.moneypig.dto.GoldSjc;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.GoldBtmcData;
import vn.vti.moneypig.models.GoldSjcData;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.GoldBtmcDataRepository;
import vn.vti.moneypig.repositories.GoldSjcDataRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GoldBtmcService {



    @Autowired
    GoldBtmcDataRepository goldBtmcDataRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DataPriceService oilPriceService;
    public GoldBtmcData create()
    {
        GoldBtmcData goldSjcData = new GoldBtmcData();
        Long id = sequenceGeneratorService.generateSequence(GoldBtmcData.SEQUENCE_NAME);
        goldSjcData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));
        Optional<GoldBtmcData> optionalFinancialData = goldBtmcDataRepository.findByUtcTime(timeUTCCurrent);
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
        GoldBtmc goldSjc = oilPriceService.getGoldBtmc();
        double priceOrigin = Double.parseDouble(goldSjc.getBuyingPrice().replace(".",""))/1000000;

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
        return goldBtmcDataRepository.insert(goldSjcData);
                //.insert(goldSjcData);
    }
    public List<GoldBtmcData> getLast20Data(){

        return  goldBtmcDataRepository.findFirst20ByOrderByIdDesc();
    }

    public List<GoldBtmcData> findAll()
    {
        return goldBtmcDataRepository.findAll();
    }

    public  boolean deleteAll()
    {
        goldBtmcDataRepository.deleteAll();
        return  true;
    }
}
