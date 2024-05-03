package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.CommandCoffeeVn;
import vn.vti.moneypig.services.CommandCoffeeVnService;

import java.util.List;

@CrossOrigin(origins = {
        "http://163.44.206.118:83",
        "http://163.44.206.118:80",
        "http://163.44.206.118:81",
        "http://localhost:3001",
        "http://localhost:3000",
        "http://150.95.113.18"
})
@RestController
@RequestMapping("/api/command-coffee-vn")
public class CommandCoffeeVnController {

    private final CommandCoffeeVnService commandCoffeeVnService;

    @Autowired
    public CommandCoffeeVnController(CommandCoffeeVnService commandCoffeeVnService) {
        this.commandCoffeeVnService = commandCoffeeVnService;
    }

    @GetMapping("/last-messages")
    public List<CommandCoffeeVn> getLastMessages() {
        return commandCoffeeVnService.getLastMessagesWithinTimeframe();
    }

    @PostMapping("/create")
    public CommandCoffeeVn create(@RequestBody CommandCoffeeVn commandCoffeeVn){

        return commandCoffeeVnService.create(commandCoffeeVn);
    }



    @GetMapping("/findPaging")
    public Page<CommandCoffeeVn> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "50") int size) {
        return commandCoffeeVnService.getPaginatedRecords(page, size);
    }
}
