package sera;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registersera")
public class MemberRegisterSera extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//회원가입은 양은 적지만 비밀번호가 있으니까 post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.data get(이름, 아이디, 비번, 이메일1, 이메일2, 전번1, 전번2, 전번3, 우편번호, 주소, 상세주소)
		
		
		
		
		
		
		
		
//		2.Logic : 1의 data를 db에 insert
		
		
		
		
//		3.response page : 2의 결과에 따라
//		3-1. !0 : 홍길동님 회원가입을 환영합니다.     //0이 아니면 성공
//		3-2.  0 : 서버문제로 회원가입이 실패하였습니다. //0이 나오면 에러 //나중에는 따로 에러잡는 페이지를 만드는게 좋다
//				   다음에 다시 시도하세요.
	}

}
