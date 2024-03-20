package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.models.Account;
import vn.vti.moneypig.services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/insert")
    public ResponseEntity<?> insert( @RequestBody Account account)
    {
        Account response =  accountService.insert(account);
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
}
