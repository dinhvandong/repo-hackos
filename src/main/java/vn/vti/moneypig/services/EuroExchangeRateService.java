package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.EuroExchangeRate;
import vn.vti.moneypig.dto.GbpExchangeRate;
import vn.vti.moneypig.dto.GoldBtmc;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.EuroExchangeRateData;
import vn.vti.moneypig.models.GbpExchangeRateData;
import vn.vti.moneypig.models.GoldBtmcData;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.EuroExchangeRateDataRepository;
import vn.vti.moneypig.repositories.GbpExchangeRateDataRepository;
import vn.vti.moneypig.repositories.GoldBtmcDataRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EuroExchangeRateService {



    @Autowired
    EuroExchangeRateDataRepository euroExchangeRateDataRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DataPriceService dataPriceService;
    public EuroExchangeRateData create()
    {
        EuroExchangeRateData euroExchangeRateData = new EuroExchangeRateData();
        Long id = sequenceGeneratorService.generateSequence(EuroExchangeRateData.SEQUENCE_NAME);
        euroExchangeRateData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));
        Optional<EuroExchangeRateData> optionalFinancialData = euroExchangeRateDataRepository.findByUtcTime(timeUTCCurrent);
        Optional<Command> lastCommand = commandRepository.findById(10L);
        Command command =  lastCommand.get();
        String timeUTC = command.getTimeUTC().substring(0,16);
        euroExchangeRateData.setUtcTime(timeUTCCurrent);
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
        EuroExchangeRate euroExchangeRate = dataPriceService.getEuroExchangeRate();
        double priceOrigin = Double.parseDouble(euroExchangeRate.getBuyingPrice().replace(",",""));

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
                euroExchangeRateData.setClose(close-randDelta);
                euroExchangeRateData.setHigh(high-randDelta);
                euroExchangeRateData.setLow(low-randDelta);
                euroExchangeRateData.setOpen(open-randDelta);
                euroExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
                euroExchangeRateData.setUp(false);
            }
            else
            {
                euroExchangeRateData.setClose(open+randDelta);
                euroExchangeRateData.setHigh(high+randDelta);
                euroExchangeRateData.setLow(low+randDelta);
                euroExchangeRateData.setOpen(close+randDelta);
                euroExchangeRateData.setUp(true);
                euroExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
            }
        }else if(value==1) {

            euroExchangeRateData.setClose(close+randDelta);
            euroExchangeRateData.setHigh(high+randDelta);
            euroExchangeRateData.setLow(low+randDelta);
            euroExchangeRateData.setOpen(open+randDelta);
            euroExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
            euroExchangeRateData.setUp(true);


        }else if(value ==-1){
            euroExchangeRateData.setUp(false);

            euroExchangeRateData.setClose(open-randDelta);
            euroExchangeRateData.setHigh(high-randDelta);
            euroExchangeRateData.setLow(low-randDelta);
            euroExchangeRateData.setOpen(close-randDelta);
            euroExchangeRateData.setUtcTime(DateUtils.getCurrentTimeUTC());
        }

        euroExchangeRateData.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        return euroExchangeRateDataRepository.insert(euroExchangeRateData);
        //.insert(goldSjcData);
    }
    public List<EuroExchangeRateData> getLast20Data(){

        return  euroExchangeRateDataRepository.findFirst20ByOrderByIdDesc();
    }

    public List<EuroExchangeRateData> findAll()
    {
        return euroExchangeRateDataRepository.findAll();
    }

    public  boolean deleteAll()
    {
        euroExchangeRateDataRepository.deleteAll();
        return  true;
    }
}