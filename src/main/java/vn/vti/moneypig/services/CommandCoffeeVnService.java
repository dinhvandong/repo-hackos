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
import vn.vti.moneypig.repositories.CommandCoffeeVnRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@Service
public class CommandCoffeeVnService {

    private final CommandCoffeeVnRepository commandCoffeeVnRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public CommandCoffeeVnService(CommandCoffeeVnRepository commandCoffeeVnRepository, MongoTemplate mongoTemplate) {
        this.commandCoffeeVnRepository = commandCoffeeVnRepository;
        this.mongoTemplate = mongoTemplate;
    }

    private final MongoTemplate mongoTemplate;
//
//    @Autowired
//    public CommandCoffeeVnService(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }

    public List<CommandCoffeeVn> getLastMessagesWithinTimeframe() {

        return commandCoffeeVnRepository.findLastMessagesWithinTimeframeGroupedByUser();
    }

    public CommandCoffeeVn create(CommandCoffeeVn commandCoffeeVn){
        Long id = sequenceGeneratorService.generateSequence(CommandCoffeeVn.SEQUENCE_NAME);
        commandCoffeeVn.setId(id);
        commandCoffeeVn.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        commandCoffeeVn.setTimeUTC(DateUtils.getCurrentTimeUTC());
        return commandCoffeeVnRepository.insert(commandCoffeeVn);
        }

    public List<CommandCoffeeVn> getRecordsWithinLast60SecondsUpdate() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime sixtySecondsAgo = currentTime.minusSeconds(60);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String sixtySecondsAgoFormatted = sixtySecondsAgo.format(formatter);

        return commandCoffeeVnRepository.findByTimeUTCGreaterThanEqual(sixtySecondsAgoFormatted);
    }

    public List<CommandCoffeeVn> getRecordsWithinLast60Seconds() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = dateFormat.format(new Date());
        Date sixtySecondsAgo = new Date(System.currentTimeMillis() - (60 * 1000));

        Query query = new Query();
        query.addCriteria(Criteria.where("time").gte(dateFormat.format(sixtySecondsAgo)).lte(currentTime));

        return mongoTemplate.find(query, CommandCoffeeVn.class);
    }

    public Page<CommandCoffeeVn> getPaginatedRecords(int page, int size) {
        Sort sort = Sort.by("id").descending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return commandCoffeeVnRepository.findAll(pageable);
    }
}
