package com.travnich.account.controller;

import com.travnich.account.entity.Document;
import com.travnich.account.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

@Controller
public class DocumentController {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostMapping("/addDocument")
    public String addDocument(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request,
            Map<String, Object> model
    ) {
        Document document = new Document(file.getOriginalFilename());
        documentRepository.save(document);
        Iterable<Document> documents = documentRepository.findAll();
        model.put("documents", documents);
        request.setAttribute("file", file);
        request.setAttribute("document", document);
        return "forward:/parseDocument";
//        return "main";

    }

    @GetMapping("/showDocument")
    public String showDocument(
            @RequestParam UUID id,
            HttpServletRequest request,
            Map<String, Object> model
    ) {
        Document document = documentRepository.findById(id).get();
        request.setAttribute("document", document);
        return "forward:/showAccounts";
    }

    @GetMapping("/main")
    public String main(
            Map<String, Object> model
    ) {
        Iterable<Document> documents = documentRepository.findAll();
        model.put("documents", documents);
        return "main";
    }
}
