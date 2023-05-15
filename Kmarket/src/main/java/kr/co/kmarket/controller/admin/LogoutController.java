package kr.co.kmarket.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.MemberVO;

@WebServlet("/admin/logout.do")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private MemberService service = MemberService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sess = req.getSession();
		MemberVO sessUser = (MemberVO) sess.getAttribute("sessUser");
		String uid = sessUser.getUid();
		
		if(sessUser != null) {
			// 세션 해제
			sess.removeAttribute("sessUser");
			sess.invalidate();
			
			// 쿠키 삭제
			Cookie cookie = new Cookie("SESSID", null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
			
			// 데이터베이스 사용자 sessId update
			service.updateUserForSessionOut(uid);
			
			System.out.println("logout session invalidate...");
		}
		
		resp.sendRedirect("/Kmarket/member/login.do?success=200");
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
