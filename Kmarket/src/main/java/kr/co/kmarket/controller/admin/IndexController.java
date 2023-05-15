package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.MemberVO;

@WebServlet("/admin/index.do")
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CsService service = CsService.INSTANCE;

	@Override
	public void init() throws ServletException {

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CsVO vo = service.selectAdminMain();
		req.setAttribute("vo", vo);
		
		Map<String, Object> index = service.selectNoticeQna();
		req.setAttribute("index", index);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/index.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
