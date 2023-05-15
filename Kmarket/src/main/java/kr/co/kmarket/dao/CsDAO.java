package kr.co.kmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import kr.co.kmarket.db.CsSql;
import kr.co.kmarket.db.DBCP;
import kr.co.kmarket.vo.CsVO;

public class CsDAO {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*** cs - index ***/
	// notice + qna 최신글 리스트 5건 출력하기 
	public List<CsVO> selectLatests(String cate1, String cate2, String cate3){
		List<CsVO> latests = new ArrayList<>();
		try {
			logger.info("selectLatests");
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(CsSql.SELECT_LATESTS);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setString(3, cate3);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				CsVO cv = new CsVO();
				cv.setNo(rs.getInt(1));
				cv.setCate(rs.getString(2));
				cv.setCateType1(rs.getString(3));
				cv.setCateType2(rs.getString(4));
				cv.setTitle(rs.getString(5));
				cv.setUid(rs.getString(6));
				cv.setRdate(rs.getString(7));
				
				latests.add(cv);
			}
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return latests;
	}
	
	/*** cs - qna ***/
	// 문의글 작성하기
	public int insertArticle(CsVO article) {
		
		int result = 0;
		
		try {
			
			logger.info("insertArticle...");
			
			Connection con = DBCP.getConnection();
			
			// 트랜잭션
			con.setAutoCommit(false);
			
			PreparedStatement psmt = con.prepareStatement(CsSql.INSERT_ARTICLE);
			Statement stmt = con.createStatement();
			
			logger.debug("cate : "+article.getCate());
			
			psmt.setString(1, article.getCate());
			psmt.setString(2, article.getCateType1());
			psmt.setString(3, article.getCateType2());
			psmt.setString(4, article.getTitle());
			psmt.setString(5, article.getContent());
			psmt.setString(6, article.getUid());
			psmt.setString(7, article.getRegip());

			psmt.executeUpdate();
			ResultSet rs = stmt.executeQuery(CsSql.SELECT_MAX_NO);

			con.commit();
			
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

	/*** cs - list ***/
	// 전체 게시물 개수
	public int selectCountTotal(String cate) {
		int total = 0;
		try {
			logger.info("selectCountTotal...");
			
			Connection con = DBCP.getConnection();
			
			PreparedStatement psmt = con.prepareStatement(CsSql.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	public int selectCountTotal(String cateType1, String cateType2) {
		int total = 0;
		try {
			logger.info("selectCountTotal...");
			
			Connection con = DBCP.getConnection();
			
			PreparedStatement psmt = con.prepareStatement(CsSql.SELECT_COUNT_TOTAL_WITH_CATE2);
			psmt.setString(1, cateType1);
			psmt.setString(2, cateType2);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	// 공지사항 - 페이지 글 가져오기 (나누면 안 되는데 수정하러 되돌아 가기 힘들어 나눕니다,,, 죄송)
	public List<CsVO> selectArticles(String cate, String cateType1, int start){
		List<CsVO> articles = new ArrayList<>();
		try {
			logger.info("CsDAO-selectArticles...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			if(cateType1 == null) {
				psmt = con.prepareStatement(CsSql.SELECT_WHOLE_ARTICLES); 
				psmt.setInt(1, start);
			}else {
				psmt = con.prepareStatement(CsSql.SELECT_ARTICLES);
				psmt.setString(1, cate);
				psmt.setString(2, cateType1);
				psmt.setInt(3, start);
			}
			
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				CsVO article = new CsVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setCate(rs.getString(3));
				article.setCateType1(rs.getString(4));
				article.setCateType2(rs.getString(5));
				article.setTitle(rs.getString(6));
				article.setContent(rs.getString(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				article.setHit(rs.getString(11));
				article.setReplyContent(rs.getString(12));
				
				articles.add(article);
				
			}
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	// 문의하기 - 페이지 글 가져오기 (나누면 안 되는데 수정하러 되돌아 가기 힘들어 나눕니다,,, 죄송)
	public List<CsVO> selectArticlesQna(String cate, String cateType1, int start){
		List<CsVO> articles = new ArrayList<>();
		try {
			logger.info("CsDAO-selectArticles...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			if(cateType1 == null) {
				 psmt = con.prepareStatement(CsSql.SELECT_WHOLE_QNA_ARTICLES);
				 psmt.setInt(1, start);
			}else {
				psmt = con.prepareStatement(CsSql.SELECT_ARTICLES);
				psmt.setString(1, cate);
				psmt.setString(2, cateType1);
				psmt.setInt(3, start);
			}
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				CsVO article = new CsVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setCate(rs.getString(3));
				article.setCateType1(rs.getString(4));
				article.setCateType2(rs.getString(5));
				article.setTitle(rs.getString(6));
				article.setContent(rs.getString(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				article.setHit(rs.getString(11));
				article.setReplyContent(rs.getString(12));
				
				articles.add(article);
				
			}
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	
	// 자주묻는 질문 - 페이지 글 가져오기 (나누면 안 되는데 수정하러 되돌아 가기 힘들어 나눕니다,,, 죄송)
		public List<CsVO> selectArticlesFaq(String cate, String cateType1, int start){
			List<CsVO> articles = new ArrayList<>();
			try {
				logger.info("CsDAO-selectfaqArticles...");
				
				Connection con = DBCP.getConnection();
				PreparedStatement psmt = null;
				
				if(cateType1 == null) {
					 psmt = con.prepareStatement(CsSql.SELECT_WHOLE_FAQ_ARTICLES);
					 psmt.setInt(1, start);
				}else {
					psmt = con.prepareStatement(CsSql.SELECT_ARTICLES);
					psmt.setString(1, cate);
					psmt.setString(2, cateType1);
					psmt.setInt(3, start);
				}
				
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					CsVO article = new CsVO();
					article.setNo(rs.getInt(1));
					article.setParent(rs.getInt(2));
					article.setCate(rs.getString(3));
					article.setCateType1(rs.getString(4));
					article.setCateType2(rs.getString(5));
					article.setTitle(rs.getString(6));
					article.setContent(rs.getString(7));
					article.setUid(rs.getString(8));
					article.setRegip(rs.getString(9));
					article.setRdate(rs.getString(10));
					article.setHit(rs.getString(11));
					
					articles.add(article);
					
				}
				rs.close();
				psmt.close();
				con.close();
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return articles;
		}
	
	// 문의하기 - 유형별 글 가져오기 (cateType1)
	public List<CsVO> selectArticlesCateType(String cateType1){
		List<CsVO> articles = new ArrayList<>();
		try {
			logger.info("selectArticles cateType1");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			psmt = con.prepareStatement(CsSql.SELECT_CATETYPE1);
			psmt.setString(1, cateType1);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				CsVO article = new CsVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setCate(rs.getString(3));
				article.setCateType1(rs.getString(4));
				article.setCateType2(rs.getString(5));
				article.setTitle(rs.getString(6));
				article.setContent(rs.getString(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				article.setHit(rs.getString(11));
				
				articles.add(article);
				
			}
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	// 문의하기 - 유형별 글 가져오기 (cateType2)
	public List<CsVO> selectArticlesCateType2(String cateType1, String cateType2, int start){
		List<CsVO> articles = new ArrayList<>();
		try {
			logger.info("selectArticlesCateType2");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			psmt = con.prepareStatement(CsSql.SELECT_CATETYPE2);
			psmt.setString(1, cateType1);
			psmt.setString(2, cateType2);
			psmt.setInt(3, start);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				CsVO article = new CsVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setCate(rs.getString(3));
				article.setCateType1(rs.getString(4));
				article.setCateType2(rs.getString(5));
				article.setTitle(rs.getString(6));
				article.setContent(rs.getString(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				article.setHit(rs.getString(11));
				article.setReplyContent(rs.getString(12));
				
				articles.add(article);
				
			}
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
		
	// 문의하기 유형2 글 가져오기 
	public List<CsVO> select_cateType_list(String cateType1, String cateType2){
		List<CsVO> articles = new ArrayList<>();
		try {
			logger.info("selectArticlesCateType2");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			psmt = con.prepareStatement(CsSql.SELECT_CATETYPE_LIST);
			psmt.setString(1, cateType1);
			psmt.setString(2, cateType2);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				CsVO article = new CsVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setCate(rs.getString(3));
				article.setCateType1(rs.getString(4));
				article.setCateType2(rs.getString(5));
				article.setTitle(rs.getString(6));
				article.setContent(rs.getString(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				article.setHit(rs.getString(11));
				article.setReplyContent(rs.getString(12));
				
				articles.add(article);
				
			}
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}	
	
	//자주묻는질문 유형2 글 가져오기
	public List<CsVO> SELECT_FAQ_CATETYPE_LIST(String cateType1, String cateType2){
		List<CsVO> articles = new ArrayList<>();
		try {
			logger.info("selectArticlesFAQCateType2");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			psmt = con.prepareStatement(CsSql.SELECT_FAQ_CATETYPE_LIST);
			psmt.setString(1, cateType1);
			psmt.setString(2, cateType2);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				CsVO article = new CsVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setCate(rs.getString(3));
				article.setCateType1(rs.getString(4));
				article.setCateType2(rs.getString(5));
				article.setTitle(rs.getString(6));
				article.setContent(rs.getString(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				article.setHit(rs.getString(11));

				
				articles.add(article);
				
			}
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}	
		
		
	/*** cs::faq list ***/
	// cate - cateType1
	public List<CsVO> selectFaqArticles(String cate, String cateType1){
		List<CsVO> articles = new ArrayList<>();
		try {
			
			logger.info("selectFaqArticles");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(CsSql.SELECT_FAQ_ARTICLES);
			psmt.setString(1, cate);
			psmt.setString(2, cateType1);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				CsVO article = new CsVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setCate(rs.getString(3));
				article.setCateType1(rs.getString(4));
				article.setCateType2(rs.getString(5));
				article.setTitle(rs.getString(6));
				article.setContent(rs.getString(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				
				articles.add(article);
			}
			
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	
	// 자주묻는질문 - 유형별 글 가져오기 (cateType2)
	public List<CsVO> selectArticlesFaqCateType2(String cateType1, String cateType2, int start){
				List<CsVO> articles = new ArrayList<>();
				try {
					logger.info("selectArticlesFaqCateType2");
					
					Connection con = DBCP.getConnection();
					PreparedStatement psmt = null;
					
					psmt = con.prepareStatement(CsSql.SELECT_FAQ_CATETYPE2);
					psmt.setString(1, cateType1);
					psmt.setString(2, cateType2);
					psmt.setInt(3, start);
					
					ResultSet rs = psmt.executeQuery();
					while(rs.next()) {
						CsVO article = new CsVO();
						article.setNo(rs.getInt(1));
						article.setParent(rs.getInt(2));
						article.setCate(rs.getString(3));
						article.setCateType1(rs.getString(4));
						article.setCateType2(rs.getString(5));
						article.setTitle(rs.getString(6));
						article.setContent(rs.getString(7));
						article.setUid(rs.getString(8));
						article.setRegip(rs.getString(9));
						article.setRdate(rs.getString(10));
						article.setHit(rs.getString(11));
						
						articles.add(article);
						
					}
					rs.close();
					psmt.close();
					con.close();
					
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				return articles;
			}
	
	/*** cs - view ***/
	public CsVO selectArticle(String no) {
		CsVO vo = null;
		
		try {
			logger.info("selectArticle");
			
			Connection con = DBCP.getConnection();
			
			con.setAutoCommit(false);
			PreparedStatement psmt = con.prepareStatement(CsSql.SELECT_ARTICLE);
			PreparedStatement psmt2 = con.prepareStatement(CsSql.UPDATE_HIT);
			
			psmt.setString(1, no);
			psmt2.setString(1, no);
			
			psmt2.executeUpdate();
			ResultSet rs = psmt.executeQuery();
			
			con.commit();
			
			if(rs.next()) {
				vo = new CsVO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setCate(rs.getString(3));
				vo.setCateType1(rs.getString(4));
				vo.setCateType2(rs.getString(5));
				vo.setTitle(rs.getString(6));
				vo.setContent(rs.getString(7));
				vo.setUid(rs.getString(8));
				vo.setRegip(rs.getString(9));
				vo.setRdate(rs.getString(10));
				vo.setReplyContent(rs.getString(12));
			}
			
			rs.close();
			psmt.close();
			psmt2.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	
	}
	/*** admin - cs - qna - reply ***/
	public CsVO selectArticleQNA(String no) {
		CsVO vo = null;
		
		try {
			logger.info("selectArticle");
			
			Connection con = DBCP.getConnection();
			
			PreparedStatement psmt = con.prepareStatement(CsSql.SELECT_ARTICLE);
			
			psmt.setString(1, no);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new CsVO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setCate(rs.getString(3));
				vo.setCateType1(rs.getString(4));
				vo.setCateType2(rs.getString(5));
				vo.setTitle(rs.getString(6));
				vo.setContent(rs.getString(7));
				vo.setUid(rs.getString(8));
				vo.setRegip(rs.getString(9));
				vo.setRdate(rs.getString(10));
				vo.setHit(rs.getString(11));
				vo.setReplyContent(rs.getString(12));
			}
			
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	
	}
	
	/*** admin - index - main  ***/
	public CsVO selectAdminMain() {
		
		CsVO vo = null;
		
		try {
			logger.debug("selectAdminMain...");
			Connection conn = DBCP.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(CsSql.SELECT_ADMIN_MAIN);
			
			if(rs.next()) {
				vo = new CsVO();
				vo.setTotalOrdNo(rs.getString(1));
				if(rs.getString(2) == null) {
					vo.setTotalOrdPrice(0);
				}else {
					vo.setTotalOrdPrice(rs.getString(2));
				}
				vo.setUid(rs.getString(3));
				vo.setTotalProd(rs.getString(4));
			}
			
			conn.close();
			stmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return vo;
	}
	
	/*** admin - cs - index  ***/
	public Map<String, Object> selectNoticeQna() {
		
		Map<String, Object> map = new HashMap<>();
		List<CsVO> qna    = null;
		List<CsVO> notice = null;
		
		try {
			logger.debug("selectNoticeQna...");
			Connection conn = DBCP.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(CsSql.SELECT_QNA_NOTICE);
			
			qna    = new ArrayList<>();
			notice = new ArrayList<>();
			
			while(rs.next()) {
				CsVO vo = new CsVO();
				String cate = rs.getString(3);
				vo.setCate(rs.getString(3));
				vo.setCateType1(rs.getString(4));
				vo.setCateType2(rs.getString(5));
				vo.setTitle(rs.getString(6));
				vo.setContent(rs.getString(7));
				vo.setUid(rs.getString(8));
				vo.setRdate(rs.getString(10));
				
				switch(cate) {
				case "qna" :
					qna.add(vo);
					break;
				case "notice" :
					notice.add(vo);
					break;
				}
				
				
			}
			
			map.put("qna", qna);
			map.put("notice", notice);
			
			conn.close();
			stmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return map;
	}

	/*** admiin - cs - notice - modify ***/
	public CsVO updateArticle(CsVO article) {
		try {
			logger.info("updateArticle");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(CsSql.UPDATE_ARTICLE);
			psmt.setString(1, article.getCate());
			psmt.setString(2, article.getCateType1());
			psmt.setString(3, article.getCateType2());
			psmt.setString(4, article.getTitle());
			psmt.setString(5, article.getContent());
			psmt.setInt(6, article.getNo());
			
			psmt.executeUpdate();
			
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return article;
	}
	
	/*** admin - cs - qna - 답변 ***/
	public CsVO updateReply(CsVO article) {
		try {
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(CsSql.UPDATE_REPLY);
			psmt.setString(1, article.getReplyContent());
			psmt.setInt(2, article.getNo());
			
			psmt.executeUpdate();
			
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return article;
	}
	
	/*** 글 삭제 ***/
	public int deleteArticle(String no) {
		int result = 0;
		try {
			logger.info("deleteArticle");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(CsSql.DELETE_ARTICLE);
			
			psmt.setString(1, no);
			
			result = psmt.executeUpdate();
			
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/*** 글 다중 삭제 ***/
	public int deleteArticles(List<String> ArticleArr) {
		int result = 0;
		int length = ArticleArr.size();
		String sql = "DELETE FROM `cs_article` WHERE `no`=?";
		
		// List의 크기만큼 조건문 실행
		for(int i=1; i<length; i++) {
			sql += " OR `no`=?";
		}
		logger.info(sql);
		
		try {
			logger.info("deleteArticles");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			// List의 크기만큼 setString 
			for(int k=0; k<length; k++) {
				psmt.setString(k+1, ArticleArr.get(k));
			}
			// result는 sql 실행 횟수를 가지고 온다
			result = psmt.executeUpdate();
			
			psmt.close();
			con.close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("result: "+result);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//faq 유형 선택 글 가져오기
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 공지사항 - 페이지 글 카테고리 별 개수 가져오기
	public int selectCountTotalNotice(String cate, String cateType1) {
		int total = 0;
		try {
			logger.info("selectCountTotal...");
			
			Connection con = DBCP.getConnection();
			
			PreparedStatement psmt = con.prepareStatement(CsSql.COUNT_NOTICE_ARTICLE);
			psmt.setString(1, cateType1);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	// 문의사항 - 페이지 글 카테고리 별 개수 가져오기
	public int selectCountTotalQna(String cateType1) {
		int total = 0;
		try {
			Connection con = DBCP.getConnection();
			
			PreparedStatement psmt = con.prepareStatement(CsSql.COUNT_QNA_ARTICLE);
			psmt.setString(1, cateType1);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			con.close();
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	// 공지사항 - 페이지 글 가져오기
	public List<CsVO> selectArticlesNotice(String cateType1){
		List<CsVO> articles = new ArrayList<>();
		try {
			logger.info("CsDAO-selectArticles...");
			
			Connection con = DBCP.getConnection();
			PreparedStatement psmt = con.prepareStatement(CsSql.SELECT_NOTICE_ARTICLE);
			psmt.setString(1, cateType1);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				CsVO article = new CsVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setCate(rs.getString(3));
				article.setCateType1(rs.getString(4));
				article.setCateType2(rs.getString(5));
				article.setTitle(rs.getString(6));
				article.setContent(rs.getString(7));
				article.setUid(rs.getString(8));
				article.setRegip(rs.getString(9));
				article.setRdate(rs.getString(10));
				article.setHit(rs.getString(11));
				article.setReplyContent(rs.getString(12));
				
				articles.add(article);
				
			}
			rs.close();
			psmt.close();
			con.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	
}
