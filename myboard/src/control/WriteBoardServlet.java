package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dao.RepBoardDAO;
import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;
import com.kitri.service.RepBoardService;


@WebServlet("/writeboard")
public class WriteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private RepBoardService service;
	public WriteBoardServlet() {
		service = new RepBoardService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");		
		String password = request.getParameter("password");
		String contents = request.getParameter("contents");
		RepBoard repBoard = new RepBoard();
		repBoard.setBoard_writer(writer);
		repBoard.setBoard_subject(subject);
		repBoard.setBoard_password(password);
		repBoard.setBoard_contents(contents);
		try {
			service.write(repBoard); //dao를 부른것
			request.setAttribute("result", "글쓰기 성공");
		}catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("result", "글쓰기 실패");
		}
		
		String path = "/writeboardresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		/*
		RepBoardDAO dao = new RepBoardDAO();
		RepBoard repBoard = new RepBoard();
		repBoard.setBoard_subject("테스트제목");
		repBoard.setBoard_writer("test");	
		repBoard.setBoard_contents("테스트내용");
		repBoard.setBoard_password("testp");
		repBoard.setParent_seq(1);//답글쓰기용 테스트
		try {
			dao.insert(repBoard);//글쓰기용 테스트
		} catch (AddException e) {
			e.printStackTrace();
		} 
	}
		
	}
		*/
	}
}
