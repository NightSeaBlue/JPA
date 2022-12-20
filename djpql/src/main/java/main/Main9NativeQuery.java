package main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.domain.Employee;

public class Main9NativeQuery {

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

		// ---------------------------------------------
		// 기존의 SQL 사용 (NOT JPQL)
		String sql = "SELECT * FROM EMP_A WHERE deptno=:dept_no";
		Query query = em.createNativeQuery(sql,Employee.class);			//Original Query 문을 보내는 경우 NativeQuery 를 사용한다.
		query.setParameter("dept_no", 20);
		List<Employee> list = query.getResultList();
		for (Employee result : list) {
			System.out.println("결과 >> " + result);
		}

	}// end of showRecord

}
