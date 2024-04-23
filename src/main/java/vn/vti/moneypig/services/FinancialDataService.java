package vn.vti.moneypig.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.FinancialData;
import vn.vti.moneypig.repositories.FinancialDataRepository;

import java.util.List;

@Service
public class FinancialDataService {

    @Autowired
    FinancialDataRepository financialDataRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    public FinancialData create(FinancialData financialData){

        Long id = sequenceGeneratorService.generateSequence(FinancialData.SEQUENCE_NAME);
        financialData.setId(id);
        return financialDataRepository.insert(financialData);
    }

    public List<FinancialData> findAll(){
        return financialDataRepository.findAll();

    }






}
