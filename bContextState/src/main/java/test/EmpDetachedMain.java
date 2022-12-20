package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpDetachedMain {

	public static void main(String[] args) {
		// 팩토리 및 엔티티매니저 생성, 전송을 위한 트랜잭션 실행
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			// Persistence Context에 Entity를 올려두기
			// Entity를 Managed 상태로 전환
			EmpVO emp1 =em.find(EmpVO.class, 1297);
			System.out.println("검색 결과 : "+emp1);
			
			// 엔티티 수정
			tx.begin();
			// 관리영역에서 emp1 분리하기
			em.detach(emp1);
			emp1.setEname("박진만");			//Managed 상태일 경우 DB의 값 수정이 가능함.
			tx.commit();		// 수정결과 반영을 위해 반드시 트랜잭션을 커밋해주어야 한다.
			
			EmpVO emp2 =em.find(EmpVO.class, 1297);		//이미 Managing 상태에 들어가있어, 메모리에 온 로드 되어 있음.
			System.out.println("검색 결과 : "+emp2);		// 따라서 DB를 거치지 않고 ContextState에 있는 결과를 그대로 가져옴
			
		} catch (Exception e) {
			System.out.println("예외 : "+e.getMessage());
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}

	}

}
