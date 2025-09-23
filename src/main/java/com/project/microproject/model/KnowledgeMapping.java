// KnowledgeMapping.java
package com.project.microproject.model;

import jakarta.persistence.*;

@Entity
public class KnowledgeMapping {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long empId;
    private Long projId;
    private Long docId;
    private String expertiseLevel; // beginner, intermediate, expert
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public Long getProjId() {
		return projId;
	}
	public void setProjId(Long projId) {
		this.projId = projId;
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public String getExpertiseLevel() {
		return expertiseLevel;
	}
	public void setExpertiseLevel(String expertiseLevel) {
		this.expertiseLevel = expertiseLevel;
	}

    // Getters and setters omitted for brevity
}
