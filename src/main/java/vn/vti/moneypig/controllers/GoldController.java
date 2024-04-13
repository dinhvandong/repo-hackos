package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.models.Category;
import vn.vti.moneypig.models.Gold;
import vn.vti.moneypig.models.Transaction;
import vn.vti.moneypig.services.GoldService;
import vn.vti.moneypig.utils.DateUtils;

@RestController
@RequestMapping("/api/gold")
public class GoldController {

    @Autowired
    GoldService goldService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestParam Long date)
    {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldService.getGoldResult(date),"success"));
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, goldService.getGoldResult(DateUtils.getCurrentDate()),"success"));
    }


    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Gold gold)
    {
        Gold response =  goldService.create(gold);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"OK"));

    }
}
