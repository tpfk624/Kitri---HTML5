import com.kitri.dto.Test;
import com.kitri.dto.TestImpl;

public class LamdaTest {
	public static void test(Test t) {
		t.m(10);
	}
	
	public static void main(String[] args) {
//		TestImpl impl = new TestImpl();
//		test(impl);
		
		//i -> System.out.println(i);
		test(i -> System.out.println(i));
		
		//람다표현식은 주로 이벤트할때 많이 쓰인다
		// i -> 필요한 매개변수?
	}
}
