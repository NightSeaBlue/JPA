package com.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DEPT_A")
public class Department {

	@Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int DEPTNO;

	private String DNAME;

	private String LOC;

	// 해당하는 부서에 속해있는 사원들을 List형태로 저장
	// Cascade = 상속관계를 무시할 수 있음.
//	@OneToMany(mappedBy = "dept",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})		
//	private List<Employee> employeeList = new ArrayList <Employee>();

}
