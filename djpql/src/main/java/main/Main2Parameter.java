package main;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.domain.Board;

public class Main2Parameter {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("djpql");

		try {
			// Board 테이블에서 1번 레코드를 화면에 출력
			// showRecord(emf);

			// 데이터 삭제
			//deleteRecord(emf);
			
			// 7788 사원의 월급을 2000으로 변경
			updateRecord(emf);

		} catch (Exception e) {
			System.out.println("예외 : " + e.getMessage());
		} finally {
			emf.close();
		}

	}

	/*
	 * 함수명 : ShowRecord 인자 : EntityManagerFactory 역할 : EntityManager를 생성해, 글번호와 일치하는
	 * 정보가 있는경우 해당 정보를 메모리에 OnLoading 함
	 * 
	 */
	static void showRecord(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		// [1] 파라미터 바인딩 (위치인자)
		/*
		 * String jpql = "SELECT seq,writer,title FROM Board AS b " +
		 * " WHERE seq=?1 and title=?2 "; Query query = em.createQuery(jpql); // Query의
		 * 경우 타입을 특정 지을수가 없음 query.setParameter(1, 1); query.setParameter(2, "임시 제목2");
		 * List<Object[]> list = query.getResultList(); // Query의 ResultList Type 은
		 * Object의 배열형
		 * 
		 * for (Object[] result : list) { System.out.println("결과 >> " +
		 * Arrays.toString(result)); }
		 */

		// [2] 파라미터 바인딩 (키워드 인자)
		String jpql = "SELECT seq,writer,title FROM Board AS b " + " WHERE seq=:seqkw and title=:titlekw ";
		Query query = em.createQuery(jpql); // Query의 경우 타입을 특정 지을수가 없음
		query.setParameter("seqkw", 2);
		query.setParameter("titlekw", "임시 제목2");
		
		// 실행
		List<Object[]> list = query.getResultList(); // Query의 ResultList Type 은 Object의 배열형
		for (Object[] result : list) {
			System.out.println("결과 >> " + Arrays.toString(result));
		}

		List<Object[]> list2 = query.getResultList(); // Query의 ResultList Type 은 Object의 배열형
		for (Object[] result : list2) {
			System.out.println("결과 >> " + Arrays.toString(result));
		}

		if (list == list2)
			System.out.println("동일 객체 입니다."); // JPQL을 사용하면, 계속해서 DB에 접근해 데이터를 가지고 옴, 즉 서로 다른 주소값을 지칭함.
		else
			System.out.println("다른 객체 입니다.");

	}// end of showRecord
	
	/*
	 * 함수명 : deleteRecord 
	 * 인자 : emf
	 * 역할 : entity manager와 JPQL을 사용해 일치하는 정보가 있는 경우 해당정보를 DB에서 삭제
	 * 
	 */
	static void deleteRecord(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		//JPQL
		String jpql = "DELETE FROM Employee AS e WHERE e.EMPNO=:emp_no";
		
		Query query = em.createQuery(jpql);
		query.setParameter("emp_no", 2);
		
		// 실행
		tx.begin();
		int result = query.executeUpdate();
		System.out.println(result+"행 만큼 삭제 수행");
		tx.commit();
		em.close();
		
	}// end of deleteRecord
	
	/*
	 * 함수명 : updateRecord
	 * 인자 : emf
	 * 역할 : entity manger와 JPQL을 사용해 일치하는 데이터가 있는경우 JPQL의 수정사항 반영 
	 *  
	 */
	static void updateRecord(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		//JPQL
		String jpql = "UPDATE FROM Employee AS e SET e.SAL=:salkw WHERE e.EMPNO=:empkw";
		
		Query query = em.createQuery(jpql);
		query.setParameter("salkw", 2000);
		query.setParameter("empkw", 7788);
		
		// 실행
		tx.begin();
		int result = query.executeUpdate();
		System.out.println(result+"행 만큼 변경 수행");
		tx.commit();
		em.close();
		
		
	} // end of updateRecord

}
