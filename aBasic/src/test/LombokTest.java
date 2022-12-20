package test;

import com.domain.TestVO;

public class LombokTest {
	public static void main(String[] args) {
		
		TestVO vo = new TestVO();
		
		vo.setName("홍길동");
		vo.setAge(22);
		
		System.out.println(vo);
		
	}
}
