package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;

@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ProductService service = new ProductService();;
		 List<Product> list = new ArrayList<Product>();
		 
		list = service.findAll();
		request.setAttribute("productlist", list);
		String path = "productlistresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
