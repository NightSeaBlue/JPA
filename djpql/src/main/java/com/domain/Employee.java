package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "EMP_A")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer EMPNO;
	
	private String ENAME;
	private String JOB;
	private Integer MGR;
	
	private Date HIREDATE;
	
	private Integer SAL;
	private Integer COMM;
	
	// ***********************
	@ManyToOne(optional = false, fetch = FetchType.LAZY)	// FetchType.LAZY : Join 하지 않고 조건에 따라 검색함					
	@JoinColumn(name = "DEPTNO")	// Join 할 Column 명
	private Department dept;
	
}
