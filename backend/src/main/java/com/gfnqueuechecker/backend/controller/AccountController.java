package com.gfnqueuechecker.backend.controller;


import com.gfnqueuechecker.backend.ErrorMessage;
import com.gfnqueuechecker.backend.entity.Account;
import com.gfnqueuechecker.backend.service.AccountService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getLastSearched(@RequestBody Account account){
        if(account.getUsername() == null || account.getUsername().trim().isEmpty()
                || account.getPassword() == null || account.getPassword().trim().isEmpty()){
            return ErrorMessage.send("User data are empty! Error!", HttpStatus.BAD_REQUEST);
        }

        Account existedAccount = this.accountService.verifyAccountExistence(account);

        if(existedAccount == null){
            return ErrorMessage.send("Wrong user data! Error!", HttpStatus.BAD_REQUEST);
        }

        String jwt = this.accountService.getJWTToken(existedAccount.getUsername(), existedAccount.getRole());
        JSONObject resp = new JSONObject();
        resp.put("Authorization", jwt);

        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }
}
