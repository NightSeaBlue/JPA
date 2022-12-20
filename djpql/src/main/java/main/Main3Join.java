package main;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.domain.Board;

public class Main3Join {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("djpql");
		// Board 테이블에서 1번 레코드를 화면에 출력
		showRecord(emf);

		try {

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
		// EntityTransaction tx = em.getTransaction();

		// ********** jpql
		// [1] 묵시적인 조인
		// String jpql = "SELECT e,e.dept FROM Employee e";

		// [2] 명시적인 조인
		String jpql = " SELECT e,d FROM" + " Employee e INNER JOIN e.dept d ";

		Query query = em.createQuery(jpql);
		// [3] 페이징 처리
		int pageNumber = 1;
		int pageSize = 3;
		int startNum = pageNumber * pageSize - pageSize;
		query.setFirstResult(startNum);
		query.setMaxResults(pageSize);

		List<Object[]> list = query.getResultList();

		for (Object[] result : list) {
			System.out.println("결과 >> " + result[0]);
			System.out.println("결과 >> " + result[1]);
		}

		em.close();

		// 트랜잭션 실행
		// tx.begin();
		// tx.commit();

	}// end of showRecord

}
