package kr.co.kmarket.service;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.vo.CsVO;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum CsService {
	INSTANCE;
	private CsDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CsService() {
		dao = new CsDAO();
	}
	
	/*** cs - index ***/
	// 최신글 리스트 5건 출력하기
	public List<CsVO> selectLatests(String cate1, String cate2, String cate3) {
		return dao.selectLatests(cate1, cate2, cate3);
	}
	
	
	/*** cs - qna ***/
	// 문의글 작성하기
	public int insertArticle(CsVO article) {
		return dao.insertArticle(article);
	}
	
	
	/*** cs - list ***/
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;	// 현재 페이지 기본값
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
		int lastPageNum = 0; // 마지막 페이지
		if(total % 10 == 0) {
			lastPageNum = (total/10);
		}else {
			lastPageNum = (total/10)+1;
		}
		return lastPageNum;
	}
	// 페이지 그룹 
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int pageGroupCurrent = (int) Math.ceil(currentPage/10.0);
		int pageGroupStart = (pageGroupCurrent - 1)*10+1;
		int pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart,pageGroupEnd};
		return result;
	}
	// 전체 게시물 개수 
	public int selectCountTotal(String cate) {
		return dao.selectCountTotal(cate);
	}
	public int selectCountTotal(String cateType1, String cateType2) {
		return dao.selectCountTotal(cateType1, cateType2);
	}
	
	/* 현재 페이지 게시글 시작값 
	public int getLimitStart(int currentPage) {
		int limitStart = 0;
		limitStart = (currentPage-1)*10;
		return limitStart;
	}
	*/
	
	// 페이지 시작 번호 
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}
	// 게시글 번호 정렬 (시작인덱스)
	public int getStartNum(int currentPage) {
		return (currentPage-1)*10;
	}
	
	// cateType1 - 마지막 페이지 번호
	public int getLastPageNum2(int total2) {
			int lastPageNum2 = 0; // 마지막 페이지
			if(total2 % 10 == 0) {
				lastPageNum2 = (total2/10);
			}else {
				lastPageNum2 = (total2/10)+1;
			}
			return lastPageNum2;
		}
	// cateType1 - 페이지 그룹 
	public int[] getPageGroupNum2(int currentPage, int lastPageNum2) {
			int pageGroupCurrent2 = (int) Math.ceil(currentPage/10.0);
			int pageGroupStart2 = (pageGroupCurrent2 - 1)*10+1;
			int pageGroupEnd2 = pageGroupCurrent2 * 10;
			
			if(pageGroupEnd2 > lastPageNum2) {
				pageGroupEnd2 = lastPageNum2;
			}
			
			int[] result = {pageGroupStart2,pageGroupEnd2};
			return result;
		}
	// cateType1 - 페이지 시작 번호 
	public int getPageStartNum2(int total2, int currentPage) {
		int start2 = (currentPage - 1) * 10;
		return total2 - start2;
	}
	
	
	
	
	
	// 공지사항 - 페이지 글 가져오기 (나누면 안 되는데 수정하러 되돌아 가기 힘들어 나눕니다,,, 죄송)
	public List<CsVO> selectArticles(String cate, String cateType1, int start){
		return dao.selectArticles(cate, cateType1, start);
	}
	// 문의하기 - 페이지 글 가져오기 (나누면 안 되는데 수정하러 되돌아 가기 힘들어 나눕니다,,, 죄송)
	public List<CsVO> selectArticlesQna(String cate, String cateType1, int start){
		return dao.selectArticlesQna(cate, cateType1, start);
	}
	//자주묻는 질문 - 페이지 글 가져오기 (나누면 안 되는데 수정하러 되돌아 가기 힘들어 나눕니다,,, 죄송)
	public List<CsVO> selectArticlesFaq(String cate, String cateType1, int start){
		return dao.selectArticlesFaq(cate, cateType1, start);
	}
	
	// 문의하기 - 유형별 글 가져오기 (cateType1)
	public List<CsVO> selectArticlesCateType(String cateType1){
		return dao.selectArticlesCateType(cateType1);
	}
	// 문의하기 - 유형별 글 가져오기 (cateType2)
	public List<CsVO> selectArticlesCateType2(String cateType1, String cateType2, int start){
		return dao.selectArticlesCateType2(cateType1, cateType2, start);
	}
	
	
	/*** cs::faq list ***/
	// cate - cateType1
	public List<CsVO> selectFaqArticles(String cate, String cateType1){
		logger.info("selectFaqArticles");
		return dao.selectFaqArticles(cate, cateType1);
	}
	
	//자주묻는질문 - 유형 2 글 가져오기
	public List<CsVO> SELECT_FAQ_CATETYPE_LIST(String cateType1, String cateType2) {
		return dao.SELECT_FAQ_CATETYPE_LIST(cateType1,cateType2);
	}
	
	
	// 문의하기 - 유형별 글 가져오기 (cateType2)
	public List<CsVO> selectArticlesFaqCateType2(String cateType1, String cateType2, int start){
			return dao.selectArticlesFaqCateType2(cateType1, cateType2, start);
	}
		
	// 문의하기 유형2 글 가져오기 
	public List<CsVO> select_cateType_list(String cateType1, String cateType2){
		return dao.select_cateType_list(cateType1, cateType2);
}
	
//	public Map<String, Object> selectFaqArticles(String cateType1, int num){
//	return dao.selectFaqArticles(cateType1, num);
//}
	
	
	/*** cs - view ***/
	public CsVO selectArticle(String no) {
		logger.info("selectArticle");
		return dao.selectArticle(no);
	}
	/*** admin - cs - qna - reply ***/
	public CsVO selectArticleQNA(String no) {
		return dao.selectArticleQNA(no);
	}
	
	/*** admin - index - main ***/
	public CsVO selectAdminMain() {
		return dao.selectAdminMain();
	}
	
	/*** admin - cs - index - notice, qna 출력  ***/
	public Map<String, Object> selectNoticeQna() {
		return dao.selectNoticeQna();
	}
	
	/*** admiin - cs - notice - modify ***/
	public CsVO updateArticle(CsVO article) {
		return dao.updateArticle(article);
	}
	
	/*** admin - cs - qna - 답변 ***/
	public CsVO updateReply(CsVO article) {
		return dao.updateReply(article);
	}
	
	
	/*** 글 삭제 ***/
	public int deleteArticle(String no) {
		return dao.deleteArticle(no);
	}
	
	/*** 글 다중 삭제 ***/
	public int deleteArticles(List<String> ArticleArr) {
		return dao.deleteArticles(ArticleArr);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 공지사항 카데고리 별 글 개수
	public int selectCountTotalNotice(String cate, String cateType1) {
		return dao.selectCountTotalNotice(cate, cateType1);
	}
	// 문의하기 카테고리 별 글 개수
	public int selectCountTotalQna(String cateType1) {
		return dao.selectCountTotalQna(cateType1);
	}
	
	// 공지사항 카테고리 별 글 보기
	public List<CsVO> selectArticlesNotice(String cateType1){
		return dao.selectArticlesNotice(cateType1);
	}
	
	
}
