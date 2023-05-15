package kr.co.kmarket.controller.admin.cs.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.AdminCsListVO;
import kr.co.kmarket.vo.CsVO;
import net.sf.json.JSONObject;

@WebServlet("/admin/cs/notice/select.do")
public class SelectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		String cate = req.getParameter("cate");
		String cateType1 = req.getParameter("cateType1");
		String cateType2 = req.getParameter("cateType2");
		String pg = req.getParameter("pg");
		String ajax = "ajax";
	
		// 
		int currentPage = service.getCurrentPage(pg);
		// 전체 게시물 개수 
		int total = service.selectCountTotalNotice(cate, cateType1);
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		// 현재 페이지 게시글 시작값
		//int limitStart = service.getLimitStart(currentPage);
		// 페이지 그룹* pageGroupStart, end
		int [] result = service.getPageGroupNum(currentPage, lastPageNum);
		// 페이지 시작 번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		// 게시글 번호 정렬
		int start = service.getStartNum(currentPage);
		
		List<CsVO> articles = service.selectArticlesNotice(cateType1);
		req.setAttribute("articles", articles);
		req.setAttribute("cate", cate);
		req.setAttribute("cateType1", cateType1);
		req.setAttribute("cateType2", cateType2);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("total", total);
		req.setAttribute("lastPageNum", lastPageNum);
		//req.setAttribute("limitStart", limitStart);
		req.setAttribute("pageStartNum", pageStartNum+1);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("ajax", ajax);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs/notice/list.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String cate = req.getParameter("cate");
		String cateType1 = req.getParameter("cateType1");
		String cateType2 = req.getParameter("cateType2");
		String pg = req.getParameter("pg");
		
		List<CsVO> articles = service.selectArticlesNotice(cateType1);
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(articles);			
		
		PrintWriter writer = resp.getWriter();
		writer.print(jsonData);
	}
}
