package kr.co.kmarket.controller.admin.cs.qna;

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

@WebServlet("/admin/cs/qna/reply.do")
public class ReplyController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 문의하기 목록 보기 
		logger.info("doGet");
		
		String cate = req.getParameter("cate");
		String cateType1 = req.getParameter("cateType1");
		String cateType2 = req.getParameter("cateType2");
		String no = req.getParameter("no");
		String replyContent = req.getParameter("replyContent");
		
		CsVO vo = service.selectArticleQNA(no);
		
		req.setAttribute("cate", cate);
		req.setAttribute("cateType1", cateType1);
		req.setAttribute("cateType2", cateType2);
		req.setAttribute("no", no);
		req.setAttribute("replyContent", replyContent);
		req.setAttribute("vo", vo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cs/qna/reply.jsp");
		dispatcher.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 문의하기 답변 보내기
		logger.info("doPost");
		
		int no = Integer.parseInt(req.getParameter("no"));
		String replyContent = req.getParameter("replyContent");
		logger.info("no: " + no);
		
		CsVO cv = new CsVO();
		cv.setNo(no);
		cv.setReplyContent(replyContent);
		
		service.updateReply(cv);
		
		resp.sendRedirect("/Kmarket/admin/cs/qna/list.do?cate=qna");
	
		
		
	}
}
