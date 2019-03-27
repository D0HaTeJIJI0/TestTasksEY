package com.travnich.account.controller;

import com.travnich.account.entity.Account;
import com.travnich.account.entity.ClassAccount;
import com.travnich.account.entity.Document;
import com.travnich.account.parser.ExcelParser;
import com.travnich.account.repo.AccountRepository;
import com.travnich.account.repo.ClassAccountRepository;
import com.travnich.account.repo.GroupAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Controller
public class AccountController {

    private final AccountRepository accountRepository;
    private final GroupAccountRepository groupAccountRepository;
    private final ClassAccountRepository classAccountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository,
                             GroupAccountRepository groupAccountRepository,
                             ClassAccountRepository classAccountRepository) {

        this.accountRepository = accountRepository;
        this.groupAccountRepository = groupAccountRepository;
        this.classAccountRepository = classAccountRepository;
    }

    @PostMapping("/parseDocument")
    public String parse(
            HttpServletRequest request
    ) {
        MultipartFile multipartFile = (MultipartFile) request.getAttribute("file");
        Document document = (Document) request.getAttribute("document");
        ExcelParser excelParser = new ExcelParser(document, multipartFile);
        excelParser.parse();
        accountRepository.saveAll(excelParser.getAccounts());
        groupAccountRepository.saveAll(excelParser.getGroupAccounts());
        classAccountRepository.saveAll(excelParser.getClassAccounts());
        return "main";

    }

    @GetMapping("/showAccounts")
    public String main(
            @RequestParam UUID id,
            HttpServletRequest request,
            Map<String, Object> model
    ) {
        Document document = (Document) request.getAttribute("document");
        Iterable<ClassAccount> classAccounts = classAccountRepository.findClassAccountByDocument(document);
        model.put("classes", classAccounts);
        return "table";
    }
}
