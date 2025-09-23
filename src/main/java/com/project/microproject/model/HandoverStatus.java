// HandoverStatus.java
package com.project.microproject.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HandoverStatus {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long fromEmp;  // employee handing over
    private Long toEmp;    // employee receiving handover
    private Long projId;
    private String status; // "pending" or "completed"
    private LocalDate date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFromEmp() {
		return fromEmp;
	}
	public void setFromEmp(Long fromEmp) {
		this.fromEmp = fromEmp;
	}
	public Long getToEmp() {
		return toEmp;
	}
	public void setToEmp(Long toEmp) {
		this.toEmp = toEmp;
	}
	public Long getProjId() {
		return projId;
	}
	public void setProjId(Long projId) {
		this.projId = projId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

    // Getters and setters omitted for brevity
}
