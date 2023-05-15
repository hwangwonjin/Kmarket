package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.ProductVO;

@WebServlet("/product/order.do")
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService service = ProductService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodNo = req.getParameter("prodNo");
		String uid    = req.getParameter("uid");
		String count  = req.getParameter("count");
		int coun = 0; 
		List<ProductVO> products = null;
		ProductVO vo = null;
		HttpSession session = req.getSession();
		
		if(count != null) {
			coun = Integer.parseInt(count);
			products = service.selectOrderProduct(prodNo, coun);
			vo = service.selectOrderTotal(prodNo, coun);
			session.setAttribute("prod", products);
			session.setAttribute("vo", vo);
			session.setAttribute("prodNo", prodNo);
		}
		
		// aside 카테고리 영역 불러오기
		Map<String, Object> cate = service.selectCategory();
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/order.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 선택한 상품만큼 cartNo가 넘어옴 이때, name=cartNo이 동일해서 배열로 저장 가능함.
		String[] cartNo = req.getParameterValues("cartNo");
		
		List<ProductVO> products = service.selectCarts(cartNo);
		
		// 선택한 상품 상품값들 가져오기. db를 한번만 access하고 싶은데.. 방법이 생각나지 않아서 두번돌림
		// 트랜잭션을 이용해서 map에 저장할 수 있을지 모르겠음 List<ProductVO> 값과 productVO값이라.
		ProductVO vo = service.selectSumCart(cartNo);
		
		// session저장 이유는 complete 페이지로 넘길시 일을 줄이기 위해서임.
		// 로그아웃 시 remove 해줘야 하지 않을까 싶음.
		HttpSession session = req.getSession();
		session.setAttribute("cartNo", cartNo);
		session.setAttribute("prod", products);
		session.setAttribute("vo", vo);
		
		resp.sendRedirect("/Kmarket/product/order.do");
	}

}
