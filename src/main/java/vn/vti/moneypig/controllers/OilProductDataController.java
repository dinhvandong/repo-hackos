package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.services.OilProductDataService;
import vn.vti.moneypig.services.OilWorldDataService;

@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/oilproductdata")
public class OilProductDataController {
    @Autowired
    OilProductDataService oilWorldDataService;
    @PostMapping("/insert")
    public ResponseEntity<?> insert()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, oilWorldDataService.create(),"success"));
    }

    @GetMapping("/find20LastRecord")
    public ResponseEntity<?> find20LastRecord()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, oilWorldDataService.getLast20Data(),"success"));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, oilWorldDataService.findAll(),"success"));
    }

    @GetMapping("/deleteAll")
    public ResponseEntity<?> deleteAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, oilWorldDataService.deleteAll(),"success"));
    }

}
