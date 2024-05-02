package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.services.EuroExchangeRateService;
import vn.vti.moneypig.services.UsdExchangeRateService;


@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/euro_exchangerate")
public class EuroExchangeRateController {

    @Autowired
    EuroExchangeRateService euroExchangeRateService;
    @PostMapping("/insert")
    public ResponseEntity<?> insert()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, euroExchangeRateService.create(),"success"));
    }

    @GetMapping("/find20LastRecord")
    public ResponseEntity<?> find20LastRecord()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, euroExchangeRateService.getLast20Data(),"success"));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, euroExchangeRateService.findAll(),"success"));
    }

    @GetMapping("/deleteAll")
    public ResponseEntity<?> deleteAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, euroExchangeRateService.deleteAll(),"success"));
    }
}