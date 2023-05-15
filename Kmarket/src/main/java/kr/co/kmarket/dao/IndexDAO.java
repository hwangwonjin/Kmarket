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

import kr.co.kmarket.db.DBCP;
import kr.co.kmarket.db.Indexsql;
import kr.co.kmarket.vo.CategoryVO;
import kr.co.kmarket.vo.ProductVO;

public class IndexDAO {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<CategoryVO> selectCate(int cate) {
		
		List<CategoryVO> category = new ArrayList<>();
		
		try {
			logger.debug("seleceCate...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(Indexsql.SELECT_CATE);
			psmt.setInt(1, cate);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				CategoryVO vo = new CategoryVO();
				vo.setCate1(rs.getInt(1));
				vo.setC1Name(rs.getString(2));
				vo.setCate2(rs.getInt(4));
				vo.setC2Name(rs.getString(5));
				
				category.add(vo);
			}
			
			conn.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return category;
	}
	
	public Map<String, Object> selectCategory() {
		
		Map<String, Object> map = null;
		List<CategoryVO> cate10 = null;
		List<CategoryVO> cate11 = null;
		List<CategoryVO> cate12 = null;
		List<CategoryVO> cate13 = null;
		List<CategoryVO> cate14 = null;
		List<CategoryVO> cate15 = null;
		List<CategoryVO> cate16 = null;
		List<CategoryVO> cate17 = null;
		List<CategoryVO> cate18 = null;
		
		try {
			logger.debug("selectCategory...");
			Connection conn = DBCP.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Indexsql.SELECT_CATEGORY);
			
			cate10 = new ArrayList<>();
			cate11 = new ArrayList<>();
			cate12 = new ArrayList<>();
			cate13 = new ArrayList<>();
			cate14 = new ArrayList<>();
			cate15 = new ArrayList<>();
			cate16 = new ArrayList<>();
			cate17 = new ArrayList<>();
			cate18 = new ArrayList<>();
			
			while(rs.next()) {
				CategoryVO vo = new CategoryVO();
				int cate = rs.getInt(1);
				vo.setCate1(rs.getInt(1));
				vo.setC1Name(rs.getString(2));
				vo.setCate2(rs.getInt(4));
				vo.setC2Name(rs.getString(5));
				
				switch(cate) {
					case 10 :
						cate10.add(vo);
						break;
					
					case 11 :
						cate11.add(vo);
						break;
						
					case 12 :
						cate12.add(vo);
						break;
						
					case 13 :
						cate13.add(vo);
						break;
						
					case 14 :
						cate14.add(vo);
						break;
						
					case 15 :
						cate15.add(vo);
						break;
						
					case 16 :
						cate16.add(vo);
						break;
						
					case 17 :
						cate17.add(vo);
						break;
						
					case 18 :
						cate18.add(vo);
						break;
				}
				
			}
			
			map = new HashMap<>();
			map.put("cate10", cate10);
			map.put("cate11", cate11);
			map.put("cate12", cate12);
			map.put("cate13", cate13);
			map.put("cate14", cate14);
			map.put("cate15", cate15);
			map.put("cate16", cate16);
			map.put("cate17", cate17);
			map.put("cate18", cate18);
			
			conn.close();
			stmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return map;
	}
	
	public List<CategoryVO> selectCate1() {
		
		List<CategoryVO> category = new ArrayList<>();
		
		try {
			logger.debug("selectCate1...");
			Connection conn = DBCP.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Indexsql.SELECT_CATE1);
			
			while(rs.next()) {
				CategoryVO vo = new CategoryVO();
				vo.setCate1(rs.getInt(1));
				vo.setC1Name(rs.getString(2));
				
				category.add(vo);
			}
			
			conn.close();
			stmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return category;
	}
	
	public List<ProductVO> selectIndex() {
		
		List<ProductVO> index = new ArrayList<>();
		
		try {
			logger.debug("selectBest...");
			Connection conn = DBCP.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Indexsql.SELECT_BEST);
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getString(1));
				vo.setProdCate1(rs.getString(2));
				vo.setProdCate2(rs.getString(3));
				vo.setProdName(rs.getString(4));
				vo.setDescript(rs.getString(5));
				vo.setPrice(rs.getInt(8));
				vo.setDiscount(rs.getInt(9));
				vo.setDelivery(rs.getInt(13));
				vo.setThumb1(rs.getString(17));
				vo.setThumb2(rs.getString(18));
				vo.setSellPrice(rs.getString(33));
				
				index.add(vo);
			}
			
			conn.close();
			stmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return index;
	}
}
