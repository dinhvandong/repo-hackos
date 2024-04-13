package vn.vti.moneypig.services;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.GoldResponse;
import vn.vti.moneypig.dto.GoldResult;
import vn.vti.moneypig.models.Gold;
import vn.vti.moneypig.repositories.GoldRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.*;

@Service
public class GoldService {
    @Autowired
    GoldRepository goldRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public Gold create(Gold newGold){

        Long id = sequenceGeneratorService.generateSequence(Gold.SEQUENCE_NAME);

        newGold.setId(id);
        newGold.setTime(DateUtils.getCurrentDate());
        return goldRepository.insert(newGold);
    }

    public Gold update(Gold updateGold){

       // Long id = sequenceGeneratorService.generateSequence(Gold.SEQUENCE_NAME);

        Optional<Gold> goldOptional = goldRepository.findById(updateGold.getId());
        if(goldOptional.isEmpty()) return  null;
        Gold goldFound = goldOptional.get();
        goldFound.setAddress(updateGold.getAddress());
        goldFound.setPriceBuy(updateGold.getPriceBuy());
        goldFound.setPriceSell(updateGold.getPriceSell());

        return goldRepository.insert(goldFound);
    }

    public Optional<Gold> findById(Long id){
        return  goldRepository.findById(id);
    }

    public List<Gold> findAll()
    {
        return goldRepository.findAll();
    }

    public List<String> getAllAddressByDate(Long currentDate)
    {
       // Long time = DateUtils.getCurrentDate();
        List<Gold> goldListByTime = goldRepository.findAllByTime(currentDate);
        Set<String> listAddress = new HashSet<>();
        for(Gold gold: goldListByTime){
            listAddress.add(gold.getAddress());
        }
       return  listAddress.stream().toList();
    }

    public GoldResult getGoldResult(Long currentDate){

        GoldResult goldResult = new GoldResult();
        List<GoldResponse> goldResponseList = getAllGoldByAddress(currentDate);
        goldResult.setDate(currentDate);
        goldResult.setGoldResponseList(goldResponseList);
        return goldResult;
    }
    public List<GoldResponse> getAllGoldByAddress(Long currentDate)
    {
        List<String> listAddress = getAllAddressByDate(currentDate);
        List<GoldResponse> goldResponseList = new ArrayList<>();
        for(String addr: listAddress){
            GoldResponse goldResponse = new GoldResponse();
            goldResponse.setAddress(addr);
            List<Gold> listByAddr = goldRepository.findAllByAddressAndTime(addr, currentDate);
            goldResponse.setGoldList(listByAddr);
            goldResponseList.add(goldResponse);
        }
        return goldResponseList;
    }

}
