package kr.co.kmarket.service;

import java.util.List;
import java.util.Map;

import kr.co.kmarket.dao.IndexDAO;
import kr.co.kmarket.vo.CategoryVO;
import kr.co.kmarket.vo.ProductVO;

public enum IndexService {
	
	INSTANCE;
	private IndexDAO dao;
	private IndexService() { dao = new IndexDAO(); }
	
	public List<CategoryVO> selectCate(int cate) {
		return dao.selectCate(cate);
	}
	
	public Map<String, Object> selectCategory() {
		return dao.selectCategory();
	}
	
	public List<CategoryVO> selectCate1() {
		return dao.selectCate1();
	}
	
	public List<ProductVO> selectIndex() {
		return dao.selectIndex();
	}
}
