package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.services.GoldBtmcService;
import vn.vti.moneypig.services.GoldSjcService;


@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/gold_btmc")
public class GoldBtmcController {

    @Autowired
    GoldBtmcService goldBtmcService;
    @PostMapping("/insert")
    public ResponseEntity<?> insert()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldBtmcService.create(),"success"));
    }

    @GetMapping("/find20LastRecord")
    public ResponseEntity<?> find20LastRecord()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldBtmcService.getLast20Data(),"success"));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldBtmcService.findAll(),"success"));
    }

    @GetMapping("/deleteAll")
    public ResponseEntity<?> deleteAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldBtmcService.deleteAll(),"success"));
    }
}