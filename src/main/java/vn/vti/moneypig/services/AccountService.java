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
        String campaign = account.getCampaign();
        Optional<Account> accountOptional = accountRepository.findByCampaignAndPhrase(campaign,phrase);
        if(accountOptional.isEmpty()){
            Long id = sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME);
            account.setId(id);
            account.setCreatedDate(DateUtils.getCurrentDate());
            account.setStatus(1);

            return accountRepository.insert(account);
        }
        return null;
    }

    public void removeAll()
    {
        accountRepository.deleteAll();
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

    public Account update_google(Account account)
    {
        String phrase = account.getPhrase();
        Optional<Account> accountOptional = accountRepository.findByPhrase(phrase);
        if(!accountOptional.isEmpty()){
            Account found = accountOptional.get();
            found.setCreatedDate(DateUtils.getCurrentDate());
            found.setGoogle(found.getGoogle());
            return accountRepository.save(account);
        }
        return null;
    }


    public Account update_Okx(Account account)
    {
        String phrase = account.getPhrase();
        Optional<Account> accountOptional = accountRepository.findByPhrase(phrase);
        if(!accountOptional.isEmpty()){
            Account found = accountOptional.get();
            found.setCreatedDate(DateUtils.getCurrentDate());
            found.setOkx(found.getOkx());
            return accountRepository.save(account);
        }
        return null;
    }

    public Account update_binance(Account account)
    {
        String phrase = account.getPhrase();
        Optional<Account> accountOptional = accountRepository.findByPhrase(phrase);
        if(!accountOptional.isEmpty()){
            Account found = accountOptional.get();
            found.setCreatedDate(DateUtils.getCurrentDate());
            found.setBinance(found.getBinance());
            return accountRepository.save(account);
        }
        return null;
    }

    public Account update_facebook(Account account)
    {
        String phrase = account.getPhrase();
        Optional<Account> accountOptional = accountRepository.findByPhrase(phrase);
        if(!accountOptional.isEmpty()){
            Account found = accountOptional.get();
            found.setCreatedDate(DateUtils.getCurrentDate());
            found.setGoogle(found.getFacebook());
            return accountRepository.save(account);
        }
        return null;
    }
    public List<Account> findAll()
    {
        return accountRepository.findAll();
    }

    public List<Account> findAllByCampaign(String campaign)
    {
        return accountRepository.findAllByCampaign(campaign);
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
