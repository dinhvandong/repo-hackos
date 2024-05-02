package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.GoldBtmc;
import vn.vti.moneypig.dto.UsdExchangeRate;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.GoldBtmcData;
import vn.vti.moneypig.models.UsdExchangeRateData;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.GoldBtmcDataRepository;
import vn.vti.moneypig.repositories.UsdExchangeRateDataRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsdExchangeRateService {



    @Autowired
    UsdExchangeRateDataRepository usdExchangeRateDataRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DataPriceService dataPriceService;
    public UsdExchangeRateData create()
    {
        UsdExchangeRateData usdExchangeRateData = new UsdExchangeRateData();
        Long id = sequenceGeneratorService.generateSequence(UsdExchangeRateData.SEQUENCE_NAME);
        usdExchangeRateData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));
        Optional<UsdExchangeRateData> optionalFinancialData = usdExchangeRateDataRepository.findByUtcTime(timeUTCCurrent);
        Optional<Command> lastCommand = commandRepository.findById(9L);
        Command command =  lastCommand.get();
        String timeUTC = command.getTimeUTC().substring(0,16);
        usdExchangeRateData.setUtcTime(timeUTCCurrent);
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
        UsdExchangeRate usdExchangeRate = dataPriceService.getUsdExchangeRate();
        double priceOrigin = Double.parseDouble(usdExchangeRate.getBuyingPrice());

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
                usdExchangeRateData.setClose(close-randDelta);
                usdExchangeRateData.setHigh(high-randDelta);
                usdExchangeRateData.setLow(low-randDelta);
                usdExchangeRateData.setOpen(open-randDelta);
                usdExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
                usdExchangeRateData.setUp(false);
            }
            else
            {
                usdExchangeRateData.setClose(open+randDelta);
                usdExchangeRateData.setHigh(high+randDelta);
                usdExchangeRateData.setLow(low+randDelta);
                usdExchangeRateData.setOpen(close+randDelta);
                usdExchangeRateData.setUp(true);
                usdExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
            }
        }else if(value==1) {

            usdExchangeRateData.setClose(close+randDelta);
            usdExchangeRateData.setHigh(high+randDelta);
            usdExchangeRateData.setLow(low+randDelta);
            usdExchangeRateData.setOpen(open+randDelta);
            usdExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
            usdExchangeRateData.setUp(true);


        }else if(value ==-1){
            usdExchangeRateData.setUp(false);

            usdExchangeRateData.setClose(open-randDelta);
            usdExchangeRateData.setHigh(high-randDelta);
            usdExchangeRateData.setLow(low-randDelta);
            usdExchangeRateData.setOpen(close-randDelta);
            usdExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
        }

        usdExchangeRateData.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        return usdExchangeRateDataRepository.insert(usdExchangeRateData);
                //.insert(goldSjcData);
    }
    public List<UsdExchangeRateData> getLast20Data(){

        return  usdExchangeRateDataRepository.findFirst20ByOrderByIdDesc();
    }

    public List<UsdExchangeRateData> findAll()
    {
        return usdExchangeRateDataRepository.findAll();
    }

    public  boolean deleteAll()
    {
        usdExchangeRateDataRepository.deleteAll();
        return  true;
    }
}
