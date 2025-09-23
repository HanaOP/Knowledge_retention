package com.project.microproject.controller;

import org.springframework.web.bind.annotation.*;
import com.project.microproject.model.Project;
import com.project.microproject.repository.ProjectRepository;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectRepository repo;

    public ProjectController(ProjectRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Project> getAll() { return repo.findAll(); }

    @PostMapping
    public Project addProject(@RequestBody Project project) { return repo.save(project); }
}
