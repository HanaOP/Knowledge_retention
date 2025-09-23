// HandoverStatusRepository.java
package com.project.microproject.repository;

import com.project.microproject.model.HandoverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandoverStatusRepository extends JpaRepository<HandoverStatus, Long> {}
