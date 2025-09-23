// EmployeeDetailsController.java
package com.project.microproject.controller;

import com.project.microproject.model.*;
import com.project.microproject.repository.*;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee-details")
public class EmployeeDetailsController {

    private final EmployeeRepository employeeRepo;
    private final KnowledgeMappingRepository kmRepo;
    private final ProjectRepository projectRepo;
    private final HandoverStatusRepository handoverRepo;

    public EmployeeDetailsController(EmployeeRepository employeeRepo,
                                     KnowledgeMappingRepository kmRepo,
                                     ProjectRepository projectRepo,
                                     HandoverStatusRepository handoverRepo) {
        this.employeeRepo = employeeRepo;
        this.kmRepo = kmRepo;
        this.projectRepo = projectRepo;
        this.handoverRepo = handoverRepo;
    }

    @GetMapping("/{empId}")
    public Map<String, Object> getEmployeeDetails(@PathVariable Long empId) {
        Map<String, Object> response = new HashMap<>();

        Optional<Employee> empOpt = employeeRepo.findById(empId);
        if (empOpt.isEmpty()) {
            response.put("error", "Employee not found");
            return response;
        }

        Employee emp = empOpt.get();
        response.put("employee", emp);

        // Find projects assigned via knowledge mappings
        List<KnowledgeMapping> mappings = kmRepo.findAll();
        List<Project> assignedProjects = new ArrayList<>();
        for (KnowledgeMapping km : mappings) {
            if (Objects.equals(km.getEmpId(), empId)) {
                projectRepo.findById(km.getProjId()).ifPresent(assignedProjects::add);
            }
        }
        response.put("projects", assignedProjects);

        // If employee inactive, include handover info
        if (!"active".equalsIgnoreCase(emp.getStatus())) {
            List<HandoverStatus> handovers = handoverRepo.findAll();
            List<HandoverStatus> relevant = new ArrayList<>();
            for (HandoverStatus h : handovers) {
                if (Objects.equals(h.getFromEmp(), empId)) {
                    relevant.add(h);
                }
            }
            response.put("handover", relevant);
        }

        return response;
    }
}
