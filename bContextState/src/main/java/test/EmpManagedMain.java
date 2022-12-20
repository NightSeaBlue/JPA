package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpManagedMain {

	public static void main(String[] args) {
		// 1299 사번 홍길숙님 정보를 입력하기
		// 1. 엔티티매니저 팩토리 생성 (패턴 중 하나)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");

		// 2. 엔티티매니저
		EntityManager em = emf.createEntityManager();

		// 4. 엔티티 트랜잭션 생성(DDL 이 있는경우만 필요함)
		EntityTransaction tx = em.getTransaction();

		try {
			// 트랜잭션 시작
			tx.begin();
			/*
			 * // 입력할 EmpVO 생성 EmpVO vo = new EmpVO(); vo.setEmpno(1297);
			 * vo.setEname("홍길동");
			 * 
			 * // 메모리 온 로드 
			 * em.persist(vo);
			 */
			// 정보 검색
			EmpVO emp1 = em.find(EmpVO.class, 1297);
			System.out.println("검색 결과 : " + emp1);

			// 정보 검색
			EmpVO emp2 = em.find(EmpVO.class, 1298);
			System.out.println("검색 결과 : " + emp2);

			// 컨텍스트의 존재 여부 확인 (Select 문을 실행해 정보가 Entity Manager 에 온 로딩 된 경우, 해당 객체를 계속 불러들일 수 있음.)
			// 컨텍스트가 중간에서 정보를 관리할 수 있음. (따라서 DB 검색의 반복성을 단축할 수 있다.)
			if(emp1==emp2) System.out.println("동일 객체 입니다.");
			else System.out.println("다른 객체 입니다.");
			
			// 트랜잭션 커밋
			tx.commit();

		} catch (Exception e) {
			System.out.println("예외 발생 : " + e.getMessage());
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}

	}

}
