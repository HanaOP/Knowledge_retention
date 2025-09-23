// DocumentRepository.java
package com.project.microproject.repository;

import com.project.microproject.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {}
