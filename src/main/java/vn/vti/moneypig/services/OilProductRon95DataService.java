package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.OilProduct;
import vn.vti.moneypig.models.OilProductData;
import vn.vti.moneypig.models.OilProductRon95Data;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.OilProductDataRepository;
import vn.vti.moneypig.repositories.OilProductRon95DataRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OilProductRon95DataService {

    @Autowired
    OilProductRon95DataRepository oilProductDataRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DataPriceService oilPriceService;
    public OilProductRon95Data create()
    {
        OilProductRon95Data oilWorldData = new OilProductRon95Data();
        Long id = sequenceGeneratorService.generateSequence(OilProductRon95Data.SEQUENCE_NAME);
        oilWorldData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));
        Optional<OilProductRon95Data> optionalFinancialData = oilProductDataRepository.findByUtcTime(timeUTCCurrent);
        Optional<Command> lastCommand = commandRepository.findById(3L);
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
        OilProduct oilPrice = oilPriceService.getOilRon95V();
        double priceOrigin = Double.parseDouble(oilPrice.getPriceZone_1().replace(',','.'));

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
                oilWorldData.setClose(close-randDelta);
                oilWorldData.setHigh(high-randDelta);
                oilWorldData.setLow(low-randDelta);
                oilWorldData.setOpen(open-randDelta);
                oilWorldData.setUtcTime(DateUtils.getCurrentTimeUTC());
                oilWorldData.setUp(false);
            }
            else
            {
                oilWorldData.setClose(open+randDelta);
                oilWorldData.setHigh(high+randDelta);
                oilWorldData.setLow(low+randDelta);
                oilWorldData.setOpen(close+randDelta);
                oilWorldData.setUp(true);
                oilWorldData.setUtcTime(DateUtils.getCurrentTimeUTC());
            }
        }else if(value==1) {

            oilWorldData.setClose(close+randDelta);
            oilWorldData.setHigh(high+randDelta);
            oilWorldData.setLow(low+randDelta);
            oilWorldData.setOpen(open+randDelta);
            oilWorldData.setUtcTime(DateUtils.getCurrentTimeUTC());
            oilWorldData.setUp(true);


        }else if(value ==-1){
            oilWorldData.setUp(false);

            oilWorldData.setClose(open-randDelta);
            oilWorldData.setHigh(high-randDelta);
            oilWorldData.setLow(low-randDelta);
            oilWorldData.setOpen(close-randDelta);
            oilWorldData.setUtcTime(DateUtils.getCurrentTimeUTC());
        }

        oilWorldData.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        return oilProductDataRepository.insert(oilWorldData);
    }
    public List<OilProductRon95Data> getLast20Data(){

        return  oilProductDataRepository.findFirst20ByOrderByIdDesc();
    }

    public List<OilProductRon95Data> findAll()
    {
        return oilProductDataRepository.findAll();
    }

    public  boolean deleteAll()
    {
        oilProductDataRepository.deleteAll();
        return  true;
    }
}
