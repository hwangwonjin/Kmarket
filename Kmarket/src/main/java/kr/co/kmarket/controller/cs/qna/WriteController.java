package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;

@WebServlet("/cs/qna/write.do")
public class WriteController extends HttpServlet{

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
		
		req.setAttribute("cate", cate);
		req.setAttribute("cateType1", cateType1);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost");
		
		// 비회원 글 작성시 (추후 구현)
    //		HttpSession session = req.getSession();
    //		String sessionID = (String) session.getAttribute("sessionID");
    //		if(sessionID == null) {
    //			sessionID = "비회원";
    //		}
		
		// 데이터 수신
		//req.setCharacterEncoding("UTF-8");
		
		String cate = req.getParameter("cate");
		String cateType1 = req.getParameter("cateType1");
		String cateType2 = req.getParameter("cateType2");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		content = content.replace("\r\n", "<br>");
		String uid = req.getParameter("uid");
		String regip = req.getRemoteAddr();	//ip는 getRemoteAddr
		
		// VO 데이터 생성
		CsVO vo =  new CsVO();
		vo.setCate(cate);
		vo.setCateType1(cateType1);
		vo.setCateType2(cateType2);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUid(uid);
		vo.setRegip(regip);
		
		// 데이터베이스 처리
		service.insertArticle(vo);
		
		
		// 리다이렉트
		resp.sendRedirect("/Kmarket/cs/qna/list.do?cate=qna&cateType1="+URLEncoder.encode(cateType1, "UTF-8"));
		// parameter가 한글이어서 생긴 에러. 다음에는 영문으로 만듭시다...
	}
}
