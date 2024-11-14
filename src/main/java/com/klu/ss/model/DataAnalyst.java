package com.klu.ss.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class DataAnalyst {
	@Id
	private long daid;
	@JoinColumn(name="uid",nullable=false)
	@OneToOne
	private User user;
	@Column(name = "specialization")
    private String specialization;
    
    @Column(name = "reports_generated")
    private Integer reportsGenerated = 0;
    
    @Column(name = "last_analysis_date")
    private LocalDateTime lastAnalysisDate;
	

}
