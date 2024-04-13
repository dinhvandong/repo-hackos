package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.CurrencyResult;
import vn.vti.moneypig.models.Currency;
import vn.vti.moneypig.models.Gold;
import vn.vti.moneypig.repositories.CurrencyRepository;
import vn.vti.moneypig.repositories.GoldRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService
{

    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public Currency create(Currency currency){

        Long id = sequenceGeneratorService.generateSequence(Currency.SEQUENCE_NAME);

        currency.setId(id);
        currency.setDate(DateUtils.getCurrentDate());
        return currencyRepository.insert(currency);
    }

    public Currency update(Currency currency){

        Optional<Currency> currencyOptional = currencyRepository.findById(currency.getId());
        if(currencyOptional.isEmpty()) return  null;
        Currency currencyFound = currencyOptional.get();
        currencyFound.setPriceBuy(currency.getPriceBuy());
        currencyFound.setPriceSell(currency.getPriceSell());
        currencyFound.setPriceTransfer(currency.getPriceTransfer());
        currencyFound.setCode(currency.getCode());

        return currencyRepository.insert(currencyFound);
    }

    public Optional<Currency> findById(Long id){
        return  currencyRepository.findById(id);
    }

    public List<Currency> findAll()
    {
        return currencyRepository.findAll();
    }
    public List<Currency> findAllByDate(Long date){
        return currencyRepository.findAllByDate(date);
    }

    public CurrencyResult getCurrencyResult (Long date){

        CurrencyResult currencyResult = new CurrencyResult();
        currencyResult.setDate(date);
        List<Currency> currencyList = findAllByDate(date);
        currencyResult.setCurrencyList(currencyList);
        return  currencyResult;
    }
}
