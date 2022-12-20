package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpMergeMain {

	public static void main(String[] args) {
		// 팩토리 및 엔티티매니저 생성, 전송을 위한 트랜잭션 실행
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			EmpVO vo = new EmpVO();
			vo.setEmpno(1235);
			vo.setEname("신사원");

			// 엔티티 삭제
			tx.begin();
			em.merge(vo);	// 일치하는 정보가 있는지 먼저 찾아보고 , 정보가 없는 경우 DB에 새로이 입력함. // 반대로 정보가 있는경우 입력이 아닌 수정이 이뤄짐.
			tx.commit(); // 수정결과 반영을 위해 반드시 트랜잭션을 커밋해주어야 한다.

		} catch (Exception e) {
			System.out.println("예외 : " + e.getMessage());
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}

	}

}
