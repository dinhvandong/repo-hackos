package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.models.CommandCoffeeArabica;
import vn.vti.moneypig.models.CommandCoffeeVn;
import vn.vti.moneypig.services.CommandCoffeeArabicaService;
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
@RequestMapping("/api/command-coffee-arabica")
public class CommandCoffeeArabicaController {

    private final CommandCoffeeArabicaService commandCoffeeVnService;

    @Autowired
    public CommandCoffeeArabicaController(CommandCoffeeArabicaService commandCoffeeVnService) {
        this.commandCoffeeVnService = commandCoffeeVnService;
    }

    @GetMapping("/last-messages")
    public List<CommandCoffeeArabica> getLastMessages() {
        return commandCoffeeVnService.getLastMessagesWithinTimeframe();
    }

    @PostMapping("/create")
    public CommandCoffeeArabica create(@RequestBody CommandCoffeeArabica commandCoffeeVn){

        return commandCoffeeVnService.create(commandCoffeeVn);
    }

    @PostMapping("/deleteAll")
    public boolean deleteAll(){

        return commandCoffeeVnService.deleteAll();
    }


    @GetMapping("/findPaging")
    public Page<CommandCoffeeArabica> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "50") int size) {
        return commandCoffeeVnService.getPaginatedRecords(page, size);
    }
}
