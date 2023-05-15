package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.ProductVO;

public enum AdminService {
	
	INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private AdminDAO dao;
	private AdminService() { dao = new AdminDAO(); }
	
	// product 상품 리스트 불러오기
	public List<ProductVO> selectProducts(int start,String search,String text) {
		return dao.selectProducts(start,search,text);
	}
	
	// 판매자(관리자)의 상품목록만 조회
	public List<ProductVO> selectProductss(int limitdstart,String uid,String search,String text) {
		return dao.selectProductss(limitdstart,uid,search,text);
	}
	
	public int selectTotal(String search, String text) {
		return dao.selectTotal(search, text);
	}
	
	public int selectCountTotal(String seller,String search,String text) {
		return dao.selectCountTotal(seller,search,text);
	}
	
	public int selectCountTotalAll(String search, String text) {
		return dao.selectCountTotalAll(search,text);
	}
	
	public List<ProductVO> selectAdminList(int limiteStart, String seller) {
		return dao.selectAdminList(seller, limiteStart);
	}
	
	
	public List<ProductVO> selectProductKeyword(String keyword,int start) {
		return dao.selectProductKeyword(keyword,start);
	}
	
	// 삭제
	public int delectProduct(String prodNo) {
		return dao.delectProduct(prodNo);
		
	}
	// 서비스 로직
	
	//페이지 그룹 계산
	public int[] getpageGroupNum(int currentPage, int lastPageNum) {
		int pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (pageGroupCurrent - 1)  * 10 + 1;
		int pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	//시작되는 페이지그룹번호
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1 ) * 10;
		return total - start;
	}
	
	//현재 페이지의 값(첫화면 표시를 위해 1)
	public int getCureentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	//시작값
	public int getStartNum(int currentPage){
		return(currentPage -1 ) * 10;
	}
	
	//마지막페이지 값
	public int getLastPageNum(int total){
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
}
