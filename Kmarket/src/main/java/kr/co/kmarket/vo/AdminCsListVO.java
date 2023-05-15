package kr.co.kmarket.vo;

import java.util.List;

public class AdminCsListVO {

	private int currentPage;
	private int lastPageNum;
	private int limitStart;
	private int pageGroupStart;
	private int pageGroupEnd;
	private int pageStartNum;
	private List<CsVO> articles;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	public int getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}
	public int getPageGroupStart() {
		return pageGroupStart;
	}
	public void setPageGroupStart(int pageGroupStart) {
		this.pageGroupStart = pageGroupStart;
	}
	public int getPageGroupEnd() {
		return pageGroupEnd;
	}
	public void setPageGroupEnd(int pageGroupEnd) {
		this.pageGroupEnd = pageGroupEnd;
	}
	public int getPageStartNum() {
		return pageStartNum;
	}
	public void setPageStartNum(int pageStartNum) {
		this.pageStartNum = pageStartNum;
	}
	public List<CsVO> getArticles() {
		return articles;
	}
	public void setArticles(List<CsVO> articles) {
		this.articles = articles;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String cateType1;
	
	public String getCateType1() {
		return cateType1;
	}
	
	public void setCateType1(String cateType1) {
		this.cateType1 = cateType1;
	}
	
	
}
