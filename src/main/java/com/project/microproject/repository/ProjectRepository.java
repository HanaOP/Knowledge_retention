// ProjectRepository.java
package com.project.microproject.repository;

import com.project.microproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {}
