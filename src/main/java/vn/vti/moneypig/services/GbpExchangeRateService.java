package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.GbpExchangeRate;
import vn.vti.moneypig.dto.GoldBtmc;
import vn.vti.moneypig.dto.UsdExchangeRate;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.GbpExchangeRateData;
import vn.vti.moneypig.models.GoldBtmcData;
import vn.vti.moneypig.models.UsdExchangeRateData;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.GbpExchangeRateDataRepository;
import vn.vti.moneypig.repositories.GoldBtmcDataRepository;
import vn.vti.moneypig.repositories.UsdExchangeRateDataRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GbpExchangeRateService {



    @Autowired
    GbpExchangeRateDataRepository gbpExchangeRateDataRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DataPriceService dataPriceService;
    public GbpExchangeRateData create()
    {
        GbpExchangeRateData gbpExchangeRateData = new GbpExchangeRateData();
        Long id = sequenceGeneratorService.generateSequence(GbpExchangeRateData.SEQUENCE_NAME);
        gbpExchangeRateData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));
        Optional<GbpExchangeRateData> optionalFinancialData = gbpExchangeRateDataRepository.findByUtcTime(timeUTCCurrent);
        Optional<Command> lastCommand = commandRepository.findById(11L);
        Command command =  lastCommand.get();
        String timeUTC = command.getTimeUTC().substring(0,16);
        gbpExchangeRateData.setUtcTime(timeUTCCurrent);
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
        GbpExchangeRate gbpExchangeRate = dataPriceService.getGbpExchangeRate();
        double priceOrigin = Double.parseDouble(gbpExchangeRate.getBuyingPrice().replace(",",""));

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
                gbpExchangeRateData.setClose(close-randDelta);
                gbpExchangeRateData.setHigh(high-randDelta);
                gbpExchangeRateData.setLow(low-randDelta);
                gbpExchangeRateData.setOpen(open-randDelta);
                gbpExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
                gbpExchangeRateData.setUp(false);
            }
            else
            {
                gbpExchangeRateData.setClose(open+randDelta);
                gbpExchangeRateData.setHigh(high+randDelta);
                gbpExchangeRateData.setLow(low+randDelta);
                gbpExchangeRateData.setOpen(close+randDelta);
                gbpExchangeRateData.setUp(true);
                gbpExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
            }
        }else if(value==1) {

            gbpExchangeRateData.setClose(close+randDelta);
            gbpExchangeRateData.setHigh(high+randDelta);
            gbpExchangeRateData.setLow(low+randDelta);
            gbpExchangeRateData.setOpen(open+randDelta);
            gbpExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
            gbpExchangeRateData.setUp(true);


        }else if(value ==-1){
            gbpExchangeRateData.setUp(false);

            gbpExchangeRateData.setClose(open-randDelta);
            gbpExchangeRateData.setHigh(high-randDelta);
            gbpExchangeRateData.setLow(low-randDelta);
            gbpExchangeRateData.setOpen(close-randDelta);
            gbpExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
        }

        gbpExchangeRateData.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        return gbpExchangeRateDataRepository.insert(gbpExchangeRateData);
        //.insert(goldSjcData);
    }
    public List<GbpExchangeRateData> getLast20Data(){

        return  gbpExchangeRateDataRepository.findFirst20ByOrderByIdDesc();
    }

    public List<GbpExchangeRateData> findAll()
    {
        return gbpExchangeRateDataRepository.findAll();
    }

    public  boolean deleteAll()
    {
        gbpExchangeRateDataRepository.deleteAll();
        return  true;
    }
}
