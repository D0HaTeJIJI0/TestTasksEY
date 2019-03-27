package com.travnich.account.controller;

import com.travnich.account.entity.Document;
import com.travnich.account.repo.AccountRepository;
import com.travnich.account.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @PostMapping
//    public Account create(@RequestBody AccountDto accountDto) {
//        return accountRepository.create(Account.createFromDto(accountDto));
//    }
}
