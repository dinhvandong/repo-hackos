package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.CommandCoffeeArabica;
import vn.vti.moneypig.models.CommandCoffeeVn;
import vn.vti.moneypig.repositories.CommandCoffeeArabicaRepository;
import vn.vti.moneypig.repositories.CommandCoffeeVnRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CommandCoffeeArabicaService {


    private final CommandCoffeeArabicaRepository commandCoffeeArabicaRepository;

     final UserService userService;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Autowired
    public CommandCoffeeArabicaService(CommandCoffeeArabicaRepository commandCoffeeArabicaRepository, UserService userService, MongoTemplate mongoTemplate) {
        this.commandCoffeeArabicaRepository = commandCoffeeArabicaRepository;
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
    }

    private final MongoTemplate mongoTemplate;
//
//    @Autowired
//    public CommandCoffeeVnService(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }

    public List<CommandCoffeeArabica> getLastMessagesWithinTimeframe() {

        return commandCoffeeArabicaRepository.findLastMessagesWithinTimeframeGroupedByUser();
    }


    public boolean deleteAll(){

        commandCoffeeArabicaRepository.deleteAll();
        return  true;
    }

    public CommandCoffeeArabica create(CommandCoffeeArabica commandCoffeeArabica){
        Long id = sequenceGeneratorService.generateSequence(CommandCoffeeVn.SEQUENCE_NAME);
        commandCoffeeArabica.setId(id);
        commandCoffeeArabica.setStatus(true);
        commandCoffeeArabica.setResult(0);
        commandCoffeeArabica.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        commandCoffeeArabica.setTimeUTC(DateUtils.getCurrentTimeUTC());
        if(commandCoffeeArabica.getMoney()<= userService.getGold(commandCoffeeArabica.getUsername()))
        {
            userService.subGold(commandCoffeeArabica.getMoney(), commandCoffeeArabica.getUsername());
            return commandCoffeeArabicaRepository.insert(commandCoffeeArabica);
        }
        return  null;

    }

    public List<CommandCoffeeArabica> getRecordsWithinLast60SecondsUpdate() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime sixtySecondsAgo = currentTime.minusSeconds(60);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String sixtySecondsAgoFormatted = sixtySecondsAgo.format(formatter);

        return commandCoffeeArabicaRepository.findByTimeUTCGreaterThanEqual(sixtySecondsAgoFormatted);
    }

    public List<CommandCoffeeVn> getRecordsWithinLast60Seconds() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = dateFormat.format(new Date());
        Date sixtySecondsAgo = new Date(System.currentTimeMillis() - (60 * 1000));

        Query query = new Query();
        query.addCriteria(Criteria.where("time").gte(dateFormat.format(sixtySecondsAgo)).lte(currentTime));

        return mongoTemplate.find(query, CommandCoffeeVn.class);
    }

    public Page<CommandCoffeeArabica> getPaginatedRecords(int page, int size) {
        Sort sort = Sort.by("id").descending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return commandCoffeeArabicaRepository.findAll(pageable);
    }
}
