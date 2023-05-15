package kr.co.kmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.a.SqlDateValueEncoder;

import kr.co.kmarket.db.DBCP;
import kr.co.kmarket.db.MemberSql;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.TermsVO;

public class MemberDAO {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/*** login ***/
	// 아이디 패스워드 확인
	public MemberVO selectMember(String uid, String pass) {
		MemberVO mv = null;
		try {
			logger.info("selectMember...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(MemberSql.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				mv = new MemberVO();
				
				mv.setUid(rs.getString(1));
				mv.setPass(rs.getString(2));
				mv.setName(rs.getString(3));
				mv.setGender(rs.getInt(4));
				mv.setHp(rs.getString(5));
				mv.setEmail(rs.getString(6));
				mv.setType(rs.getInt(7));
				mv.setPoint(rs.getInt(8));
				mv.setLevel(rs.getInt(9));
				mv.setZip(rs.getString(10));
				mv.setAddr1(rs.getString(11));
				mv.setAddr2(rs.getString(12));
				mv.setCompany(rs.getString(13));
				mv.setCeo(rs.getString(14));
				mv.setBizRegNum(rs.getString(15));
				mv.setCornRegNum(rs.getString(16));
				mv.setTel(rs.getString(17));
				mv.setManager(rs.getString(18));
				mv.setManagerHp(rs.getString(19));
				mv.setFax(rs.getString(20));
				mv.setRegip(rs.getString(21));
				mv.setWdate(rs.getString(22));
				mv.setRdate(rs.getString(23));
				mv.setEtc1(rs.getInt(24));
				mv.setEtc2(rs.getInt(25));
				mv.setEtc3(rs.getString(26));
				mv.setEtc4(rs.getString(27));
				mv.setEtc5(rs.getString(28));
				
			}
			
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mv;
	}

	// 자동 로그인 (sessID) DB 저장
	public void updateMemberForSession(String sessId, String uid) {
		try {
			logger.info("updateMemberForSession...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(MemberSql.UPDATE_MEMBER_FOR_SESSION);
			psmt.setString(1, sessId);
			psmt.setString(2, uid);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	
	/*** logout - LogoutContrller  ***/
	public void updateUserForSessionOut(String uid) {
		try {
			logger.info("updateUserForSessionOut...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(MemberSql.UPDATE_MEMBER_FOR_SESSION_OUT);
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	
	/*** register - RegisterController ***/
	// 회원가입
	public int insertMember(MemberVO vo) {
		
		int result = 0;
		
		try {
			logger.info("insertMember...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(MemberSql.INSERT_MEMBER);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setInt(4, vo.getGender());
			psmt.setString(5, vo.getHp());
			psmt.setString(6, vo.getEmail());
			psmt.setString(7, vo.getZip());
			psmt.setString(8, vo.getAddr1());
			psmt.setString(9, vo.getAddr2());
			psmt.setString(10, vo.getRegip());
			
			result = psmt.executeUpdate();
			
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return result;
	}
  
	// 아이디 중복확인
	public int selectCountUid(String uid) {
		int result = 0;
		
		try {
			logger.info("selectCountUid...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(MemberSql.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
  
  /*** terms ***/
	// 약관(일반회원)
	public TermsVO selectTerms() {
		
		TermsVO vo = null;
		
		try {
			Connection con = DBCP.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(MemberSql.SELECT_TERMS);
			
			if(rs.next()) {
				vo = new TermsVO();
				vo.setTerms(rs.getString(1));
				vo.setFinance(rs.getString(2));
				vo.setPrivacy(rs.getString(3));
				vo.setLocation(rs.getString(4));
			}
			con.close();
			stmt.close();
			rs.close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	/*** terms ***/
	// 약관(판매자회원)
	public TermsVO selectTax() {
		
		TermsVO vo = null;
		
		try {
			Connection con = DBCP.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(MemberSql.SELECT_TAX);
			
			if(rs.next()) {
				vo = new TermsVO();
				vo.setTax(rs.getString(1));
				vo.setFinance(rs.getString(2));
				vo.setPrivacy(rs.getString(3));
			}
			con.close();
			stmt.close();
			rs.close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}





	/*** register - RegisterSellerController 
	 * @return ***/
	public int insertSellerMember(MemberVO vo) {
		
		int result = 0;
		
		try {
			logger.info("insertSellerMember...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(MemberSql.INSERT_SELLER_MEMBER);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getCompany());
			psmt.setString(4, vo.getCeo());
			psmt.setString(5, vo.getBizRegNum());
			psmt.setString(6, vo.getCornRegNum());
			psmt.setString(7, vo.getTel());
			psmt.setString(8, vo.getFax());
			psmt.setString(9, vo.getEmail());
			psmt.setString(10, vo.getZip());
			psmt.setString(11, vo.getAddr1());
			psmt.setString(12, vo.getAddr2());
			psmt.setString(13, vo.getManager());
			psmt.setString(14, vo.getManagerHp());
			psmt.setString(15, vo.getRegip());
			
			result = psmt.executeUpdate();
			
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return result;
	}
	

	public int selectCountSellerUid(String uid) {
		int result = 0;
		
		try {
			logger.info("selectCountSellerUid...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(MemberSql.SELECT_COUNT_SELLER_UID);
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			con.close();
			
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return result;
	}

	
	




}
