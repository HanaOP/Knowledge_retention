package com.project.microproject.controller;

import org.springframework.web.bind.annotation.*;
import com.project.microproject.model.Document;
import com.project.microproject.repository.DocumentRepository;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentRepository repo;

    public DocumentController(DocumentRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Document> getAll() { return repo.findAll(); }

    @PostMapping
    public Document addDocument(@RequestBody Document doc) { return repo.save(doc); }
}