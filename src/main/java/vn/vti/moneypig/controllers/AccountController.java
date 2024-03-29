package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.models.Account;
import vn.vti.moneypig.services.AccountService;

import java.util.List;
@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert( @RequestBody Account account)
    {
        Account response =  accountService.insert(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"insert Ok"));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update( @RequestBody Account account)
    {
        Account response =  accountService.update(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"insert Ok"));
    }

    @GetMapping("/removeAll")
    public ResponseEntity<?> removeAll( )
    {
        accountService.removeAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "delete all","delete Ok"));
    }

    @PostMapping("/update_binance")
    public ResponseEntity<?> update_binance( @RequestBody Account account)
    {
        Account response =  accountService.update_binance(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"insert Ok"));
    }


    @PostMapping("/update_okx")
    public ResponseEntity<?> update_okx( @RequestBody Account account)
    {
        Account response =  accountService.update_Okx(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"insert Ok"));
    }
    @PostMapping("/update_google")
    public ResponseEntity<?> update_google( @RequestBody Account account)
    {
        Account response =  accountService.update_google(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"insert Ok"));
    }
    @PostMapping("/update_facebook")
    public ResponseEntity<?> update_facebook( @RequestBody Account account)
    {
        Account response =  accountService.update_facebook(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"insert Ok"));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestParam String token)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"Token blank"));
        }
        token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            List<Account> accountList = accountService.findAllUpdate();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, accountList,"Ok"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(202, null,"Not authenticated"));
        }
    }


    @GetMapping("/findAllNoToken")
    public ResponseEntity<?> findAllNoToken()
    {
        List<Account> accountList = accountService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, accountList,"Ok"));

    }




    @GetMapping("/findAllByCampaign")
    public ResponseEntity<?> findAllByCampaign(@RequestParam String campaign)
    {
        List<Account> accountList = accountService.findAllByCampaign(campaign);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, accountList,"Ok"));

    }



    @GetMapping("/findMemeo")
    public ResponseEntity<?> findMemeo(@RequestParam String token)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"Token blank"));
        }
        token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            List<Account> accountList = accountService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, accountList,"Ok"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(202, null,"Not authenticated"));
        }
    }
}
