package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpRemovedMain {

	public static void main(String[] args) {
		// 팩토리 및 엔티티매니저 생성, 전송을 위한 트랜잭션 실행
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			// Persistence Context에 Entity를 올려두기
			// Entity를 Managed 상태로 전환
			EmpVO emp1 =em.find(EmpVO.class, 1299);
			System.out.println("검색 결과 : "+emp1);
			
			// 엔티티 삭제
			tx.begin();
			em.remove(emp1);	// DB에 접근해 일치하는 정보를 삭제함.
			tx.commit();		// 수정결과 반영을 위해 반드시 트랜잭션을 커밋해주어야 한다.
			
			EmpVO emp2 =em.find(EmpVO.class, 1299);		// Managing 에 OnLoading 된 정보가 없으므로 DB와 다시 연결해, 일치하는 정보를 가지고 오려고 함. 
			System.out.println("검색 결과 : "+emp2);		// 일치하는 정보가 없으므로 Null 로 리턴
			
		} catch (Exception e) {
			System.out.println("예외 : "+e.getMessage());
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}

	}

}
