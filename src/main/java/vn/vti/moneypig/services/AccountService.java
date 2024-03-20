package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Account;
import vn.vti.moneypig.repositories.AccountRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    public Account insert(Account account)
    {
        String phrase = account.getPhrase();
        Optional<Account> accountOptional = accountRepository.findByPhrase(phrase);
        if(accountOptional.isEmpty()){
            Long id = sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME);
            account.setId(id);
            account.setCreatedDate(DateUtils.getCurrentDate());
            return accountRepository.insert(account);
        }
        return null;
    }

    public Account update(Account account)
    {
        String phrase = account.getPhrase();
        Optional<Account> accountOptional = accountRepository.findByPhrase(phrase);
        if(!accountOptional.isEmpty()){
            Account found = accountOptional.get();
            found.setCreatedDate(DateUtils.getCurrentDate());
            found.setBinance(account.getBinance());
            found.setFacebook(account.getFacebook());
            found.setGoogle(found.getGoogle());
            return accountRepository.save(account);
        }
        return null;
    }
    public List<Account> findAll()
    {
        return accountRepository.findAll();
    }
    public List<Account> findAllUpdate()
    {
        if(accountRepository.findAll().size()>=100)
        {
            List<Account> accountAll = accountRepository.findAll();
            int lengthMax = accountAll.size();
            int subLength = (int) ((int)lengthMax*0.2);
           return  accountAll.subList(0,lengthMax-subLength);
        }
        else
        {
            return accountRepository.findAll();
        }
    }



}
