// KnowledgeMappingRepository.java
package com.project.microproject.repository;

import com.project.microproject.model.KnowledgeMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeMappingRepository extends JpaRepository<KnowledgeMapping,Long> {}
