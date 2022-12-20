package com.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data	// lombok : setter,getter,toString...

// entity
@Entity
@Table(name = "EMP_Z")
public class EmpVO2 {

	@Id	// persistence
	// 오라클인 경우
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	// mariaDB/MySql
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empno;
	
	@Column(length = 30)
	private String ename;
	
	@Column(length = 30,nullable = true)
	private String job;
	
	// DB의 칼럼명과 DTO의 인자의 이름이 같지 않은 경우
	@Column(name = "hire_date")
	private Date hiredate;
	
	@Column(precision = 5,scale = 0)
	private int sal;
	
	// column check = 칼럼에 해당하는 값이 있는지 없는지 확인하고, 있는 경우 입력
	@Column(columnDefinition = "int check(deptno in(87,88,89))")
	private int deptno;
	
}
