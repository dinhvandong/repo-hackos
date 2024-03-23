package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.models.AppConfig;
import vn.vti.moneypig.repositories.AppConfigRepository;

import java.util.Optional;

@CrossOrigin(origins = "http://150.95.110.230")
@RestController
@RequestMapping("/api/config")
public class AppConfigController {
    @Autowired
    AppConfigRepository appConfigRepository;
    @GetMapping()
    public ResponseEntity<?> signUp() {

        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200, appConfigRepository.findById(1L),""));
    }

    @PostMapping()
    public ResponseEntity<?> signUp(@RequestParam int config) {

        Optional<AppConfig> appConfigOptional = appConfigRepository.findById(1L);
        if(appConfigOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body
                    (new ResponseObject(201, "false", "Not found ID=1"));

        }
        AppConfig appConfigFound = appConfigOptional.get();
        appConfigFound.setActive(config);
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200, appConfigRepository.save(appConfigFound), ""));

    }
}
