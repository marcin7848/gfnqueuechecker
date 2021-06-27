package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.entity.Account;
import com.gfnqueuechecker.backend.repository.AccountRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account verifyAccountExistence(Account account){
        PasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        Account existedAccount = this.accountRepository.findByUsername(account.getUsername());
        if(existedAccount == null)
            return null;

        if(!bCryptEncoder.matches(account.getPassword(), existedAccount.getPassword())){
            return null;
        }

        return existedAccount;
    }

    public String getJWTToken(String username, Long role) {
        String secretKey = "gfnqueuecheckersecret@K";

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(role == 3){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            grantedAuthorities.add(authority);
        }

        String token = Jwts
                .builder()
                .setId("gfnQueueJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
