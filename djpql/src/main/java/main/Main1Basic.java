package main;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.domain.Board;

public class Main1Basic {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("djpql");
		// Board 테이블에서 1번 레코드를 화면에 출력
		showRecord(emf);
		
		try {
			
		} catch (Exception e) {
			System.out.println("예외 : "+e.getMessage());
		} finally {
			emf.close();
		}

	}

	/*
	 *	함수명 : ShowRecord
	 *	인자 : EntityManagerFactory 
	 * 	역할 : EntityManager를 생성해, 글번호와 일치하는 정보가 있는경우 해당 정보를 메모리에 OnLoading 함
	 * 
	 */
	static void showRecord(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		//EntityTransaction tx = em.getTransaction();
		
		// ********** jpql
		// [1] 검색결과를 특정할 수 있는 경우 : TypedQuery
		/*String jpql = "SELECT b FROM Board AS b";
		TypedQuery query = em.createQuery(jpql,Board.class);
		
		List<Board> list = query.getResultList();
		
		for(Board result : list) {
			System.out.println("결과 >> " + result);
		}*/
		
		// [2] 검색 결과를 특정할 수 없는 경우 : Query
		String jpql = "SELECT seq,writer,title FROM Board AS b";
		Query query = em.createQuery(jpql);						// Query의 경우 타입을 특정 지을수가 없음
		
		List<Object[]> list = query.getResultList();			// Query의 ResultList Type 은 Object의 배열형
		
		for(Object[] result : list) {
			System.out.println("결과 >> " + Arrays.toString(result));
		}
		
		// 트랜잭션 실행
		//tx.begin();		
		//tx.commit();
		
	}// end of showRecord
	
	
}
