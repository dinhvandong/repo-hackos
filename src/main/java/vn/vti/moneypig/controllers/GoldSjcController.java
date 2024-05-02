package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.services.CoffeeRobustaService;
import vn.vti.moneypig.services.GoldSjcService;


@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/gold_sjc")
public class GoldSjcController {

    @Autowired
    GoldSjcService goldSjcService;
    @PostMapping("/insert")
    public ResponseEntity<?> insert()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldSjcService.create(),"success"));
    }

    @GetMapping("/find20LastRecord")
    public ResponseEntity<?> find20LastRecord()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldSjcService.getLast20Data(),"success"));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldSjcService.findAll(),"success"));
    }

    @GetMapping("/deleteAll")
    public ResponseEntity<?> deleteAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldSjcService.deleteAll(),"success"));
    }
}