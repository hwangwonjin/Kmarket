package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.CategoryVO;
import kr.co.kmarket.vo.ProductVO;

@WebServlet("/product/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService service = ProductService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("listController doget...");
		
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String pg = req.getParameter("pg");
		String sort  = req.getParameter("sort");

		//상품 네비게이션
		CategoryVO category = service.selectCate(cate1, cate2);
		req.setAttribute("category", category);
		
		//현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		//전체 게시물 갯수 구하기
		int total = service.selectCountTotal(cate1, cate2);
		
		//페이지 마지막 번호 계산
		int lastPageNum = service.getLastPageNum(total);
		
		//페이지 그룹 start, end 계산
		int[] result = service.getpageGroupNum(currentPage, lastPageNum);
		
		//페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		//시작 인덱스
		int start = service.getStartNum(currentPage);
		
		// 상품목록 출력
	 	List<ProductVO> products = service.selectProducts(start, cate1, cate2, sort);
	 	Map<String, Object> cate = service.selectCategory();
		
	 	req.setAttribute("products", products);
	 	req.setAttribute("cate1", cate1);
	 	req.setAttribute("cate2", cate2);
	 	req.setAttribute("pg", pg);
	 	req.setAttribute("currentPage", currentPage);
	 	req.setAttribute("lastPageNum", lastPageNum);
	 	req.setAttribute("pageGroupStart", result[0]);
	 	req.setAttribute("pageGroupEnd", result[1]);
	 	req.setAttribute("pageStartNum", pageStartNum+1);
	 	req.setAttribute("sort", sort);
	 	req.setAttribute("cate", cate);
	 	
	 	logger.info("produtcts : " +products);
	 	logger.info("cate1 : " +cate1);
	 	logger.info("cate2 : " +cate2);
	 	logger.info("category : "+category);
	 	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
