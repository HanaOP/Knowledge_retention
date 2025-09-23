// EmployeeRepository.java
package com.project.microproject.repository;

import com.project.microproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
