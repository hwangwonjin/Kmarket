package kr.co.kmarket.controller.admin.cs.faq;

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

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;

@WebServlet("/admin/cs/faq/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cate = req.getParameter("cate");
		String cateType1 = req.getParameter("cateType1");
		String cateType2 = req.getParameter("cateType2");
		String pg = req.getParameter("pg");
		
		logger.info("cate : " +cate);
		logger.info("cateType1 : "+cateType1);
		logger.info("cateType2 : "+cateType2);
		
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		// 전체 게시물 개수
		int total = service.selectCountTotal(cate);
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		// 페이지 그룹
		int [] result = service.getPageGroupNum(currentPage, lastPageNum);
		// 페이지 시작 번호 
		int pageStartNum = service.getPageStartNum(total, currentPage);
		// 게시글 번호 정렬
		int start = service.getStartNum(currentPage);
		
		// 페이지 글 가져오기
		List<CsVO> articles = service.selectArticlesFaq(cate, cateType1, start);
		
		req.setAttribute("cate", cate);
		req.setAttribute("cateType1", cateType1);
		req.setAttribute("cateType2", cateType2);
		req.setAttribute("articles", articles);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("total", total);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs/faq/list.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
