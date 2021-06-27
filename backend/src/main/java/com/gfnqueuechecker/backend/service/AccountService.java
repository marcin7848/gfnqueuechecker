package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.entity.Account;
import com.gfnqueuechecker.backend.repository.AccountRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public boolean verifyAccountExistence(Account account){
        PasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        Account existedAccount = this.accountRepository.findByUsername(account.getUsername());
        if(existedAccount == null)
            return false;

        return  bCryptEncoder.matches(account.getPassword(), existedAccount.getPassword());
    }

    public String getJWTToken(String username) {
        String secretKey = "gfnqueuecheckersecret@K";

        String token = Jwts
                .builder()
                .setId("gfnQueueJWT")
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
