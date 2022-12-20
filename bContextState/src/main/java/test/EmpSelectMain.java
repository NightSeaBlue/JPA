package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpSelectMain {

	public static void main(String[] args) {
		// 팩토리 및 엔티티매니저 생성, 전송을 위한 트랜잭션 실행
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			// Persistence Context에 Entity를 올려두기
			// [1] find() : 일치하는 정보가 있는 경우 해당하는 정보를 Managing 상태로 Onloading 해줌 / 일치하는 정보가 없을 때 Null 로 Return
			//EmpVO emp1 =em.find(EmpVO.class, 1297); // 객체가 생성될 때 Query 문이 전송됨
			//System.out.println("검색 결과 : "+emp1);
			
			//[2] getReference() : 
			//EmpVO emp2 =em.getReference(EmpVO.class,1298);	// 일치하는 정보가 있는 경우 해당하는 정보를 Managing 상태로 OnLoading 해줌 / 일치하는 정보가 없을 때 예외발생	
			//System.out.println("검색 결과 : "+emp2);		// 객체를 호출하는 시점에 Query 문이 전송됨.
			
			//[3] JPQL (Java Persistence Query Language)
			// 	createQuery()
			// 	==> 테이블 명이 아닌 엔티티명을 사용 (반드시 대소문자 구별!!!!!)
			//***************************************************************
			String jpql ="SELECT e FROM EmpVO e ORDER BY e.empno DESC";				//SQL QUERY 문이 아닌 JPQL
			List<EmpVO> list = em.createQuery(jpql,EmpVO.class).getResultList();
			
			for (EmpVO vo : list) {
				System.out.println(">>>>"+ vo);
			}
				
			
		} catch (Exception e) {
			System.out.println("예외 : "+e.getMessage());		
		} finally {
			em.close();
			emf.close();
		}

	}

}
