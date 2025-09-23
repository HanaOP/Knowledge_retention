// HandoverStatusController.java
package com.project.microproject.controller;

import com.project.microproject.model.*;
import com.project.microproject.repository.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RestController
@RequestMapping("/handover-status")
public class HandoverStatusController {

    private final HandoverStatusRepository repo;
    private final ProjectRepository projectRepo;
    private final EmployeeRepository employeeRepo;

    public HandoverStatusController(HandoverStatusRepository repo, ProjectRepository projectRepo, EmployeeRepository employeeRepo) {
        this.repo = repo;
        this.projectRepo = projectRepo;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/employee/{empId}")
    public List<Map<String, String>> getHandoverStatus(@PathVariable Long empId) {
        List<Map<String, String>> result = new ArrayList<>();
        for (HandoverStatus hs : repo.findAll()) {
            if (hs.getFromEmp().equals(empId)) {
                Project proj = projectRepo.findById(hs.getProjId()).orElse(null);
                Map<String, String> map = new HashMap<>();
                map.put("projectName", proj != null ? proj.getTitle() : "Unknown");
                map.put("status", hs.getStatus());
                result.add(map);
            }
        }
        return result;
    }

    @PostMapping
    public HandoverStatus add(@RequestBody HandoverStatus hs) {
        return repo.save(hs);
    }

    public static class HandoverUpdate {
        private Long projId;
        private String status;

        // Getters and setters
        public Long getProjId() { return projId; }
        public void setProjId(Long projId) { this.projId = projId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    @PutMapping("/update/{empId}")
    @Transactional
    public ResponseEntity<?> updateHandoverStatus(@PathVariable Long empId, @RequestBody List<HandoverUpdate> updates) {
        // Update statuses
        for (HandoverUpdate update : updates) {
            for (HandoverStatus hs : repo.findAll()) {
                if (hs.getFromEmp().equals(empId) && hs.getProjId().equals(update.getProjId())) {
                    hs.setStatus(update.getStatus());
                    repo.save(hs);
                    break;
                }
            }
        }

        // Check if all handovers completed and employee inactive => delete employee and handovers
        boolean allCompleted = repo.findAll().stream()
            .filter(hs -> hs.getFromEmp().equals(empId))
            .allMatch(hs -> "completed".equalsIgnoreCase(hs.getStatus()));

        employeeRepo.findById(empId).ifPresent(emp -> {
            if (!"active".equalsIgnoreCase(emp.getStatus()) && allCompleted) {
                // Delete handovers
                repo.findAll().stream()
                    .filter(hs -> hs.getFromEmp().equals(empId))
                    .forEach(repo::delete);
                // Delete employee
                employeeRepo.delete(emp);
            }
        });

        return ResponseEntity.ok("Updated");
    }
}
