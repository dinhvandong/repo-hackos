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


    // Lay gia OiL dau tien

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
        int value = 0;// tao random
        // Co giá trị -1 0 1
        // Giá trị = 0 có nghĩa là không làm gì
        // Giá trị  = -1 là kéo xuống
        // Giá trị = 1 là kéo lên
        if(timeUTC.equals(timeUTCCurrent)){
             value = command.getValue();
            // 0 1 2 | 0 la giam, 1 random 2 tang
        }

        return financialDataRepository.insert(financialData);
    }

    public List<FinancialData> findAll(){
        return financialDataRepository.findAll();

    }
}
