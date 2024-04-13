package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.models.Currency;
import vn.vti.moneypig.models.Gold;
import vn.vti.moneypig.services.CurrencyService;
import vn.vti.moneypig.services.GoldService;
import vn.vti.moneypig.utils.DateUtils;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestParam Long date)
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, currencyService.getCurrencyResult(date),"success"));
    }

    @PostMapping("/findAll")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, currencyService.getCurrencyResult(DateUtils.getCurrentDate()),"success"));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Currency gold)
    {
        Currency response =  currencyService.create(gold);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"OK"));

    }
}
