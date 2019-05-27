import java.io.*;
import java.util.Date;

import com.kitri.dto.Child;
import com.kitri.dto.Product;

public class SerializeTest {
	public static void main(String[] args) {
		/*
		 * 직렬화
		 * file-name : a.ser
		 * 바이트단위 출력스트림: FileOutputStream
		 * 객체출력스트림 : ObjectOutputStream
		 */
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("a.ser"));
			Date now = new Date();
			Product p = new Product();
			p.setProd_no("001"); p.setProd_name("아메리카노"); 
			p.setProd_price(2500); //가격정보는 transient를 줘서 직렬화가 되지 않아 0값으로 뜬다
			
			Child c = new Child();
			c.setC("Child Instance Variable");
			
			oos.writeObject(now); //직렬화
			oos.writeObject(p); //직렬화
			oos.writeObject(c); //직렬화
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		/*
		 * 역직렬화
		 * file-name: a.ser
		 * ByteInputStream: FileInputStream
		 * ObjectInputStream
		 */
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("a.ser"));
			Object obj = ois.readObject();
			System.out.println(obj);
			
			obj = ois.readObject();
			System.out.println(obj);
			
			obj = ois.readObject();
			Child c1 = (Child)obj;
			System.out.println(c1.getC()); //child에 Serializable를 구현해서 자식만 직렬화 만약 부모에 구현했다면 자식까지도 직렬화 된다
			System.out.println(c1.getP()); //직렬화의 대상으로 삼은게 child여서 차일드만 직렬화가 됨(자식영역만 직렬화)
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}