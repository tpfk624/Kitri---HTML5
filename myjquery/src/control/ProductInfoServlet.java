package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;


@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet 들어옴");
		Product product = new Product();
		
		String no = (String)request.getParameter("no");
		System.out.println("no :" + no);
		ProductService service = new ProductService();
		product = service.findByNo(no);
		String path = "/productinforesult.jsp";
		
		
		request.setAttribute("product", product);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}