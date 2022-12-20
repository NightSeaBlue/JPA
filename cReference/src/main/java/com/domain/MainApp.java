package com.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainApp {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cReference");

		try {

			// [1] 연관관계를 이용한 데이터 검색
			//selectData(emf);
			
			// [2] 연관관계를 이용한 데이터 입력
			//insertData(emf);
			
			// [3] 연관관계를 이용한 데이터 수정
			// updateData(emf);
			
			// [4] 연관관계를 이용한 데이터 삭제
			deleteData(emf);

		} catch (Exception e) {
			System.out.println("예외 : " + e.getMessage());
		} finally {
			emf.close();
		} // end of try-catch-finally

	}

	// [1] 연관관계를 이용한 데이터 검색
	/*
	 * 함수명 : selectData
	 * 역할 : 사원 번호를 검색해 정보가 있는 경우, EntityManager 를 이용해 정보를 가져옴
	 * 
	 */
	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		Employee e1 =em.find(Employee.class, 7788);
		System.out.println(e1);
		System.out.println(e1.getENAME()+"님 정보");
		System.out.println("부서 : "+e1.getDept().getDNAME());

		em.close();
	} // end of selectData
	
	// [2] 연관관계를 이용한 데이터 입력
	/*
	 * 함수명 : insertData
	 * 역할 : emf 를 이용해 EntityManager 를 생성해, 정보를 입력함
	 * 
	 */
	static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Employee emp1 = new Employee();
		emp1.setENAME("이덕수");		
		// Department class 를 가지고 올 때, 특정 조건을 만족하는 class를 가지고 온다.
		//Department dept = em.find(Department.class, 40);
		
		// 새로운 부서가 생기고, 해당하는 부서에 신규 직원이 입사하는 경우
		Department dept = new Department();
		dept.setDEPTNO(50);
		dept.setDNAME("IT");
		dept.setLOC("인천");
		em.persist(dept);
		emp1.setDept(dept);		
		em.persist(emp1);
		
		tx.commit();
		em.close();
		
	} // end of insertData
	
	// [3] 연관 관계를 이용한 데이터 수정
	/*
	 * 함수명 : updateData
	 * 역할 : 이름으로 사원의 정보를 찾아 부서번호를 변경함. 이를  Entity Manager를 이용해 관리 
	 * 
	 */
	static void updateData(EntityManagerFactory emf) {
		// 사번이 7369인 사원의 부서 번호를 40번으로 변경함.
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// 사원의 정보를 찾아 Employ Class 에 저장하고 사번의 정보를 찾아 Department class 에 저장
		// 이후 해당하는 클래스들을 Entity Manager를 이용해 On-Loading
		Employee emp = em.find(Employee.class, 7369);
		Department dept = em.find(Department.class,40);
		//em.persist(dept); 이미 메모리에 온로드 되어 있으므로, 따로 persist 하지 않아도 됨
		emp.setDept(dept);
		em.persist(emp);
		
		tx.commit();
		em.close();
		
		
	} // end of updateData
	
	// [4] 연관관계를 이용한 데이터 삭제
	/*
	 *	함수명 : deleteData
	 *	인자 : EntityManagerFactory
	 *	역할 : 일치하는 정보를 찾아 해당하는 정보를 DataBase에서 삭제
	 * 
	 */
	static void deleteData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// 40번 부서를 삭제
		Department dept = em.find(Department.class, 40);
		em.remove(dept);
		//System.out.println(dept);
		// [해결 1] 사원 테이블에서 40번 부서의 내용을 Null로 수정
		// [해결 2] 40번 부서의 사원 정보를 먼저 삭제 (CASCADE 설정)
		tx.commit();
		em.close();
		
	}// end of deleteData
	
}
