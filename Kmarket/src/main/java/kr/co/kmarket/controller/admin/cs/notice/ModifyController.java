package kr.co.kmarket.controller.admin.cs.notice;

import java.io.IOException;

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

@WebServlet("/admin/cs/notice/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		logger.info("doGet");
		
		String cate = req.getParameter("cate");
		String cateType1 = req.getParameter("cateType1");
		String cateType2 = req.getParameter("cateType2");
		String no = req.getParameter("no");
		
		CsVO vo  = service.selectArticle(no);
		
		req.setAttribute("cate", cate);
		req.setAttribute("cateType1", cateType1);
		req.setAttribute("cateType2", cateType2);
		req.setAttribute("no", no);
		req.setAttribute("vo", vo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs/notice/modify.jsp");
		dispatcher.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost");
		
		// 데이터 수신
		req.setCharacterEncoding("UTF-8");
		
		String cate = req.getParameter("cate");
		String cateType1 = req.getParameter("cateType1");
		String cateType2 = req.getParameter("cateType2");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		content = content.replace("\r\n", "<br>");
		int no = Integer.parseInt(req.getParameter("no"));
		String uid = req.getParameter("uid");
		String regip = req.getRemoteAddr();	//ip는 getRemoteAddr
		
		logger.info("title: " + title);
		logger.info("content: " + content);
		
		// VO 데이터 생성
		CsVO vo2 =  new CsVO();
		vo2.setCate(cate);
		vo2.setCateType1(cateType1);
		vo2.setCateType2(cateType2);
		vo2.setTitle(title);
		vo2.setContent(content);
		vo2.setNo(no);
		vo2.setUid(uid);
		vo2.setRegip(regip);
		
		// 데이터베이스 처리
		service.updateArticle(vo2);
		
		// 리다이렉트
		resp.sendRedirect("/Kmarket/admin/cs/notice/list.do?cate=notice");
	}
	
}
