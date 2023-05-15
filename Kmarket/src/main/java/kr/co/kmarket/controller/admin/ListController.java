package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.ProductVO;

@WebServlet("/admin/product/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private AdminService service = AdminService.INSTANCE;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void init() throws ServletException {

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pg = req.getParameter("pg");
		String level = req.getParameter("level");
		String seller = req.getParameter("seller");
		String search = req.getParameter("search");
		String text = req.getParameter("text");
		
		//현재 페이지번호
		int total = 0;
		
		if(level.equals("7")) {
			total = service.selectTotal(search, text);
		}else {
			total = service.selectCountTotal(uid,search,text);
		}
		
		int currentPage = service.getCureentPage(pg);
		int lastPageNum = service.getLastPageNum(total);
		int[] result = service.getpageGroupNum(currentPage, lastPageNum);
		int pageStartNum = service.getPageStartNum(total, currentPage);
		int start = service.getStartNum(currentPage);
		
		//level 7 상품전체보기
		if(level.equals("7")) {
			List<ProductVO>  Product = service.selectProducts(start,search,text);
			req.setAttribute("Product", Product);
		}else {
			List<ProductVO> Product = service.selectProductss(start,uid,search,text);
			req.setAttribute("Product", Product);
		}
		
		req.setAttribute("seller", seller);
		req.setAttribute("pg", pg);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("level", level);
		req.setAttribute("search", search);
		req.setAttribute("text", text);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
