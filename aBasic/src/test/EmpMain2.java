package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.domain.EmpVO2;

public class EmpMain2 {

	public static void main(String[] args) {
		
		// 1. 엔티티매니저 팩토리 생성 (패턴 중 하나)
			EntityManagerFactory emf =
					Persistence.createEntityManagerFactory("aBasic");
		
		// 2. 엔티티매니저
		EntityManager em=emf.createEntityManager();
		
		// 4. 엔티티 트랜잭션 생성(DDL 이 있는경우만 필요함)
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			// 3. 엔티티 생성
			EmpVO2 vo = new EmpVO2();
			//vo.setEmpno(9802);
			vo.setEname("과연");
			vo.setDeptno(87);
			
			// 4. 트랜잭션 처리
			tx.begin();
			em.persist(vo);
			tx.commit();
			
		} catch (Exception e) {
			System.out.println("작업 실패 : "+e.getMessage());
			tx.rollback();
		} finally {
			// 매니저 및 팩토리 종료
			em.close();
			emf.close();
		}

	}

}
