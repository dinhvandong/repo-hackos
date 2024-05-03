package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.CommandCoffeeArabica;
import vn.vti.moneypig.models.CommandCoffeeVn;
import vn.vti.moneypig.repositories.CommandCoffeeVnRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
@Service
public class CommandCoffeeVnService {

    private final CommandCoffeeVnRepository commandCoffeeVnRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public CommandCoffeeVnService(CommandCoffeeVnRepository commandCoffeeVnRepository) {
        this.commandCoffeeVnRepository = commandCoffeeVnRepository;
    }

    public List<CommandCoffeeVn> getLastMessagesWithinTimeframe() {

        return commandCoffeeVnRepository.findLastMessagesWithinTimeframeGroupedByUser();
    }

    public CommandCoffeeVn create(CommandCoffeeVn commandCoffeeVn){
        Long id = sequenceGeneratorService.generateSequence(CommandCoffeeVn.SEQUENCE_NAME);
        commandCoffeeVn.setId(id);
        return commandCoffeeVnRepository.insert(commandCoffeeVn);
        }
}
