package vn.vti.moneypig.services;


import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.FinancialData;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.FinancialDataRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FinancialDataService {

    @Autowired
    FinancialDataRepository financialDataRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Autowired
    CommandRepository commandRepository;
    public FinancialData create(FinancialData financialData){




        Long id = sequenceGeneratorService.generateSequence(FinancialData.SEQUENCE_NAME);
        financialData.setId(id);
        String timeUTCCurrent = (DateUtils.getCurrentTimeUTC().substring(0,16));


        Optional<FinancialData> optionalFinancialData = financialDataRepository.findByUtcTime(timeUTCCurrent);

        Optional<Command> lastCommand = commandRepository.findById(1L);
        Command command =  lastCommand.get();
        String timeUTC = command.getTimeUTC().substring(0,16);
        financialData.setUtcTime(timeUTCCurrent);
        int value = 1;// tao random
        if(timeUTC.equals(timeUTCCurrent)){
             value = command.getValue();
            // 0 1 2 | 0 la giam, 1 random 2 tang
        }


        int []arrayRandom  = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int []speed = {-1,0, 1};
        if(optionalFinancialData.isEmpty()){
            // Create New

            FinancialData lastRecord = financialDataRepository.findFirstByOrderByIdDesc();

            FinancialData newRecord = new FinancialData();
            newRecord.setId(id);
            newRecord.setUtcTime(timeUTC);
            newRecord.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());

            if(value==0){

                // Tao ngau nhien

                int indexRandom = new Random().nextInt(3);

                int sppedValue = speed[indexRandom];


                newRecord.setHigh(lastRecord.getHigh());
                newRecord.setLow(lastRecord.getLow());
                newRecord.setOpen(lastRecord.getClose());
                newRecord.setClose(lastRecord.getClose());


            }







        }
        else
        {


            // Update



        }





        return financialDataRepository.insert(financialData);
    }

    public List<FinancialData> findAll(){
        return financialDataRepository.findAll();

    }






}
