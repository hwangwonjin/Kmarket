package kr.co.kmarket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.TermsVO;

public enum MemberService {
	
	INSTANCE;
	private MemberDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService() {
		dao = new MemberDAO();
	}
	
	/*** login - LoginController ***/
	// 아이디 패스워드 확인
	public MemberVO selectMember(String uid, String pass) {
		return dao.selectMember(uid, pass);
	}
	// 자동 로그인 (sessID) DB 저장
	public void updateMemberForSession(String uid, String sessId) {
		dao.updateMemberForSession(uid, sessId);
	}
	
	/*** logout - LogoutContrller ***/
	public void updateUserForSessionOut(String uid) {
		dao.updateUserForSessionOut(uid);
	}
	
	/*** register - RegisterController ***/
	// 회원가입
	public int insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}
  
  // 아이디 중복 확인
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	/*** register - RegisterSellerController ***/
	public int insertSellerMember(MemberVO vo) {
		return dao.insertSellerMember(vo);
	}
	
	//아이디 중복확인
	public int selectCountSellerUid(String uid) {
		return dao.selectCountSellerUid(uid);
	}
	
	/*** terms ***/
	// 약관(일반회원)
	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	
	// 약관(판매자회원)
	public TermsVO selectTax() {
		return dao.selectTax();
	}
}
