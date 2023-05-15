package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.TermsVO;

@WebServlet("/member/signupSeller.do")
public class SignupSellerController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.INSTANCE;

	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TermsVO vo = service.selectTax();
		req.setAttribute("vo",vo);
		
		
		logger.info("SignupSellerController doGet...");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/signupSeller.jsp");
		dispatcher.forward(req, resp);
	
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		logger.info("SignupSellerController doPost...");
		
	}
	
}
