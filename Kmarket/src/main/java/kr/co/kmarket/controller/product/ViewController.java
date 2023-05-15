package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.ProductVO;

@WebServlet("/product/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService service = ProductService.INSTANCE;
	private IndexService ser = IndexService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodNo = req.getParameter("prodNo");
		String uid = req.getParameter("sessUid");
		req.setAttribute("uid", uid);
		
		ProductVO product = service.selectProduct(prodNo);
		req.setAttribute("product", product);
		
		Map<String, Object> cate = ser.selectCategory();
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/view.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		String prodNo = req.getParameter("prodNo");
		
		List<ProductVO> products = service.selectReview(prodNo);
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(products);			
		
		PrintWriter writer = resp.getWriter();
		writer.print(jsonData);
		
	}

}
