package vn.vti.moneypig.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.models.AppConfig;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.repositories.AppConfigRepository;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.Date;
import java.util.Optional;
@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/command")
public class CommandController {

    @Autowired
    CommandRepository commandRepository;
    @GetMapping()
    public ResponseEntity<?> getCommand() {

        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200, commandRepository.findById(1L),""));
    }

    @PostMapping()
    public ResponseEntity<?> setCommand(@RequestParam int command) {

        Optional<Command> commandOptional = commandRepository.findById(1L);
        if(commandOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body
                    (new ResponseObject(201, "false", "Not found ID=1"));

        }
        Command commandFound = commandOptional.get();
        commandFound.setValue(command);
        commandFound.setTime(DateUtils.getCurrentDate());
        commandFound.setTimeUTC(DateUtils.getCurrentTimeUTC());
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200, commandRepository.save(commandFound), ""));

    }

}
