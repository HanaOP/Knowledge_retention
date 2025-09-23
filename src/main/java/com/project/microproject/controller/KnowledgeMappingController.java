package com.project.microproject.controller;

import org.springframework.web.bind.annotation.*;
import com.project.microproject.model.KnowledgeMapping;
import com.project.microproject.model.Project;
import com.project.microproject.repository.KnowledgeMappingRepository;
import com.project.microproject.repository.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping("/knowledge-mappings")
public class KnowledgeMappingController {

    private final KnowledgeMappingRepository repo;
    private final ProjectRepository projectRepo;

    public KnowledgeMappingController(KnowledgeMappingRepository repo, ProjectRepository projectRepo) {
        this.repo = repo;
        this.projectRepo = projectRepo;
    }

    // Return Projects for an Employee
    @GetMapping("/employee/{empId}")
    public List<Project> getProjectsForEmployee(@PathVariable Long empId) {
        List<KnowledgeMapping> mappings = repo.findAll().stream()
                .filter(km -> km.getEmpId().equals(empId))
                .toList();

        return mappings.stream()
                .map(km -> projectRepo.findById(km.getProjId()).orElse(null))
                .filter(p -> p != null)
                .toList();
    }

    @PostMapping
    public KnowledgeMapping add(@RequestBody KnowledgeMapping km) {
        return repo.save(km);
    }
}
