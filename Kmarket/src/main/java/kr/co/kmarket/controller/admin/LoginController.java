package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.MemberVO;

@WebServlet("/admin/login.do")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private MemberService service = MemberService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/login.jsp");
		dispatcher.forward(req, resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		MemberVO vo = service.selectMember(uid, pass);
		
		int type = vo.getType();
		
		if(vo != null  && type == 2) {
			// 회원이 맞을 경우
			HttpSession sess = req.getSession();
			sess.setAttribute("sessUser", vo);
			
			// 세션 유지시간 1시간 
			sess.setMaxInactiveInterval(60*60) ;
			
			
			System.out.println("--- admin::로그인 성공 ---");
			
			resp.sendRedirect("/Kmarket/admin/index.do");
		}else {
			// 회원이 아닐 경우
			System.out.println("LoginController - 로그인 실패");
			resp.setContentType("text/html;charset=UTF-8"); 
            PrintWriter out = resp.getWriter(); 
            out.println("<script>"); 
            out.println("alert('The ID or password you entered is incorrect.');"); 
            out.println("history.back();"); 
            out.println("</script>"); 
            out.close();
			resp.sendRedirect("/Kmarket/admin/login.do?success=100");
		}
		
	}
	
}
