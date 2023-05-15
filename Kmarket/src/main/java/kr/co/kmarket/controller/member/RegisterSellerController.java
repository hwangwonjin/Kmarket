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
import kr.co.kmarket.vo.MemberVO;

@WebServlet("/member/registerSeller.do")
public class RegisterSellerController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private MemberService service = MemberService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		logger.info("RegisterSellerController doGet...");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/registerSeller.jsp");
		dispatcher.forward(req, resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		logger.info("RegisterSellerController doPost...");
		
		String uid = req.getParameter("km_uid");
		String pass = req.getParameter("km_pass1");
		String company = req.getParameter("kms_company");
		String ceo = req.getParameter("kms_ceo");
		String corp = req.getParameter("kms_corp_reg");
		String online = req.getParameter("kms_online_reg");
		String tel = req.getParameter("kms_tel");
		String fax = req.getParameter("kms_fax");
		String email = req.getParameter("kms_email");
		String zip = req.getParameter("kms_zip");
		String addr1 = req.getParameter("kms_addr1");
		String addr2 = req.getParameter("kms_addr2");
		String manager = req.getParameter("km_name");
		String managerHp = req.getParameter("km_hp");
		String regip = req.getRemoteAddr();
		
		MemberVO vo = new MemberVO();
		vo.setUid(uid);
		vo.setPass(pass);
		vo.setCompany(company);
		vo.setCeo(ceo);
		vo.setBizRegNum(corp);
		vo.setCornRegNum(online);
		vo.setTel(tel);
		vo.setFax(fax);
		vo.setEmail(email);
		vo.setZip(zip);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setManager(manager);
		vo.setManagerHp(managerHp);
		vo.setRegip(regip);
		
		//데이터 처리
		int result = service.insertSellerMember(vo);
		
		if(result == 1) {
			// 리다이렉트
			resp.sendRedirect("/Kmarket/member/login.do?success=101");
		}else if(result == 0) {
			// 리다이렉트
			resp.sendRedirect("/Kmarket/member/login.do?success=102");
		}
	}
	
}
