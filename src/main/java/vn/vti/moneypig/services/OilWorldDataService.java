package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.FinancialData;
import vn.vti.moneypig.models.OilPrice;
import vn.vti.moneypig.models.OilWorldData;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.OilWorldDataRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OilWorldDataService {
    @Autowired
    OilWorldDataRepository oilWorldDataRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    OilPriceService oilPriceService;
   public  OilWorldData create()
   {
       OilWorldData oilWorldData = new OilWorldData();
        Long id = sequenceGeneratorService.generateSequence(OilWorldData.SEQUENCE_NAME);
        oilWorldData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));
        Optional<OilWorldData> optionalFinancialData = oilWorldDataRepository.findByUtcTime(timeUTCCurrent);
        Optional<Command> lastCommand = commandRepository.findById(1L);
        Command command =  lastCommand.get();
        String timeUTC = command.getTimeUTC().substring(0,16);
        oilWorldData.setUtcTime(timeUTCCurrent);
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
        OilPrice oilPrice = oilPriceService.getOilPrice2();
        double priceOrigin = Double.parseDouble(oilPrice.getPrice().replace(',','.'));

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
        //============================================================================
        // 0 or 1;
        if(value ==0)
        {
            if(randInt == 0)
            {
                oilWorldData.setClose(close);
                oilWorldData.setHigh(high);
                oilWorldData.setLow(low);
                oilWorldData.setOpen(open);
                oilWorldData.setUtcTime(DateUtils.getCurrentTimeUTC());
                oilWorldData.setUp(false);
            }
            else
            {
                oilWorldData.setClose(open);
                oilWorldData.setHigh(high);
                oilWorldData.setLow(low);
                oilWorldData.setOpen(close);
                oilWorldData.setUp(true);
                oilWorldData.setUtcTime(DateUtils.getCurrentTimeUTC());
            }
        }else if(value==1) {

            oilWorldData.setClose(close);
            oilWorldData.setHigh(high);
            oilWorldData.setLow(low);
            oilWorldData.setOpen(open);
            oilWorldData.setUtcTime(DateUtils.getCurrentTimeUTC());
            oilWorldData.setUp(true);


        }else if(value ==-1){
            oilWorldData.setUp(false);

            oilWorldData.setClose(open);
            oilWorldData.setHigh(high);
            oilWorldData.setLow(low);
            oilWorldData.setOpen(close);
            oilWorldData.setUtcTime(DateUtils.getCurrentTimeUTC());
        }

        oilWorldData.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        return oilWorldDataRepository.insert(oilWorldData);
    }
     public List<OilWorldData> getLast20Data(){

        return  oilWorldDataRepository.findFirst20ByOrderByIdDesc();
    }

    public List<OilWorldData> findAll()
    {
        return oilWorldDataRepository.findAll();
    }

    public  boolean deleteAll()
    {
         oilWorldDataRepository.deleteAll();
        return  true;
    }
}
