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
import vn.vti.moneypig.models.CommandCoffeeRobusta;
import vn.vti.moneypig.models.CommandCoffeeVn;
import vn.vti.moneypig.repositories.CommandCoffeeRobustaRepository;
import vn.vti.moneypig.repositories.CommandCoffeeVnRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CommandCoffeeRobustaService {


    private final CommandCoffeeRobustaRepository commandCoffeeRobustaRepository;

     final UserService userService;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Autowired
    public CommandCoffeeRobustaService(
            CommandCoffeeRobustaRepository commandCoffeeVnRepository,
            UserService userService, MongoTemplate mongoTemplate) {
        this.commandCoffeeRobustaRepository = commandCoffeeVnRepository;
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
    }

    private final MongoTemplate mongoTemplate;
//
//    @Autowired
//    public CommandCoffeeVnService(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }

    public List<CommandCoffeeRobusta> getLastMessagesWithinTimeframe() {

        return commandCoffeeRobustaRepository.findLastMessagesWithinTimeframeGroupedByUser();
    }


    public boolean deleteAll(){

        commandCoffeeRobustaRepository.deleteAll();
        return  true;
    }

    public CommandCoffeeRobusta create(CommandCoffeeRobusta commandCoffeeRobusta){
        Long id = sequenceGeneratorService.generateSequence(CommandCoffeeVn.SEQUENCE_NAME);
        commandCoffeeRobusta.setId(id);
        commandCoffeeRobusta.setStatus(true);
        commandCoffeeRobusta.setResult(0);
        commandCoffeeRobusta.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        commandCoffeeRobusta.setTimeUTC(DateUtils.getCurrentTimeUTC());
        if(commandCoffeeRobusta.getMoney()<= userService.getGold(commandCoffeeRobusta.getUsername()))
        {
            userService.subGold(commandCoffeeRobusta.getMoney(), commandCoffeeRobusta.getUsername());
            return commandCoffeeRobustaRepository.insert(commandCoffeeRobusta);
        }
        return  null;

    }

    public List<CommandCoffeeRobusta> getRecordsWithinLast60SecondsUpdate() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime sixtySecondsAgo = currentTime.minusSeconds(60);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String sixtySecondsAgoFormatted = sixtySecondsAgo.format(formatter);

        return commandCoffeeRobustaRepository.findByTimeUTCGreaterThanEqual(sixtySecondsAgoFormatted);
    }

    public List<CommandCoffeeVn> getRecordsWithinLast60Seconds() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = dateFormat.format(new Date());
        Date sixtySecondsAgo = new Date(System.currentTimeMillis() - (60 * 1000));

        Query query = new Query();
        query.addCriteria(Criteria.where("time").gte(dateFormat.format(sixtySecondsAgo)).lte(currentTime));

        return mongoTemplate.find(query, CommandCoffeeVn.class);
    }

    public Page<CommandCoffeeRobusta> getPaginatedRecords(int page, int size) {
        Sort sort = Sort.by("id").descending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return commandCoffeeRobustaRepository.findAll(pageable);
    }
}
