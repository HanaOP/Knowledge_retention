package com.project.microproject.controller;

import org.springframework.web.bind.annotation.*;
import com.project.microproject.model.Employee;
import com.project.microproject.repository.EmployeeRepository;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

 // existing @RestController, @RequestMapping("/employees")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }


    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return repo.save(employee);
    }
}