package kr.co.kmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.AdminSql;
import kr.co.kmarket.db.DBCP;
import kr.co.kmarket.vo.ProductVO;

public class AdminDAO {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<ProductVO> selectProducts(int start,String search, String text) {
		List<ProductVO> products = new ArrayList<>();
		
		try {
			logger.debug("selectProducts...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = null;
			if(search == "" || search == null) {
				psmt = conn.prepareStatement(AdminSql.selectProduct);
				psmt.setInt(1, start);
			}else if(search.equals("prodName")) {
				psmt = conn.prepareStatement(AdminSql.selectProduct1);
				psmt.setString(1, "%"+text+"%");
				psmt.setInt(2, start);
			}else if(search.equals("prodNo")) {
				psmt = conn.prepareStatement(AdminSql.selectProduct2);
				psmt.setString(1, "%"+text+"%"); 
				psmt.setInt(2, start);
			}else if(search.equals("company")) {
				psmt = conn.prepareStatement(AdminSql.selectProduct3);
				psmt.setString(1, "%"+text+"%");
				psmt.setInt(2, start);
			}else if(search.equals("seller")) {
				psmt = conn.prepareStatement(AdminSql.selectProduct4);
				psmt.setString(1, "%"+text+"%");
				psmt.setInt(2, start);
			}
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductVO product = new ProductVO();
				product.setThumb1(rs.getString(17));
				product.setProdNo(rs.getInt(1));
				product.setProdName(rs.getString(4));
				product.setPrice(rs.getString(8));
				product.setDiscount(rs.getInt(9));
				product.setPoint(rs.getInt(10));
				product.setStock(rs.getInt(11));
				product.setSeller(rs.getString(7));
				product.setHit(rs.getInt(14));
				
				products.add(product);
			}
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
		return products;
	}
	
	public List<ProductVO> selectProductss(int limitestart,String uid,String search,String text) {
		
		List<ProductVO> products = new ArrayList<>();
		
		try {
			logger.debug("selectProductss...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = null;
			if(search == "" || search == null){
				psmt = conn.prepareStatement(AdminSql.selectProductss);
				psmt.setString(1, uid);
				psmt.setInt(2, limitestart);
			}else if(search.equals("prodName")){
				psmt = conn.prepareStatement(AdminSql.selectProductss1);
				psmt.setString(1, uid);
				psmt.setString(2, "%"+text+"%");
				psmt.setInt(3, limitestart);
			}else if(search.equals("prodNo")){
				psmt = conn.prepareStatement(AdminSql.selectProductss2);
				psmt.setString(1, uid);
				psmt.setString(2, "%"+text+"%");
				psmt.setInt(3, limitestart);
			}else if(search.equals("company")){
				psmt = conn.prepareStatement(AdminSql.selectProductss3);
				psmt.setString(1, uid);
				psmt.setString(2, "%"+text+"%");
				psmt.setInt(3, limitestart);
			}else if(search.equals("seller")){
				psmt = conn.prepareStatement(AdminSql.selectProductss4);
				psmt.setString(1, uid);
				psmt.setString(2, "%"+text+"%");
				psmt.setInt(3, limitestart);
			}
			
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductVO product = new ProductVO();
				product.setThumb1(rs.getString(17));
				product.setProdNo(rs.getInt(1));
				product.setProdName(rs.getString(4));
				product.setPrice(rs.getString(8));
				product.setDiscount(rs.getInt(9));
				product.setPoint(rs.getInt(10));
				product.setStock(rs.getInt(11));
				product.setSeller(rs.getString(7));
				product.setHit(rs.getInt(14));
				
				
				products.add(product);
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return products;
	}
	
	public int selectTotal(String search, String text) {
		int total = 0;
		
		try {
			logger.info("selectCountTotal...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			if(search == "" || search == null) {
				psmt = conn.prepareStatement(AdminSql.SELECT_TOTAL);
			}else if(search.equals("prodName")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_for_search1All);
				psmt.setString(1, "%"+text+"%");
			}else if(search.equals("prodNo")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_for_search2All);
				psmt.setString(1, "%"+text+"%");
			}else if(search.equals("company")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_for_search3All);
				psmt.setString(1, "%"+text+"%");
			}else if(search.equals("seller")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_for_search4All);
				psmt.setString(1, "%"+text+"%");
			}
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	public int selectCountTotal(String seller,String search,String text) {
		int total = 0;
		
		try {
			logger.info("selectCountTotal...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			if(search == "" || search == null){
				psmt = conn.prepareStatement(AdminSql.SELECT_COUNT_TOTAL);
				psmt.setString(1, seller);
			}else if(search.equals("prodName")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_search1);
				psmt.setString(1, seller);
				psmt.setString(2, "%"+text+"%");
			}else if(search.equals("prodNo")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_search2);
				psmt.setString(1, seller);
				psmt.setString(2, "%"+text+"%");
			}else if(search.equals("company")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_search3);
				psmt.setString(1, seller);
				psmt.setString(2, "%"+text+"%");
			}else if(search.equals("seller")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_search4);
				psmt.setString(1, seller);
				psmt.setString(2, "%"+text+"%");
			}
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	public int selectCountTotalAll(String search, String text) {
		int total = 0;
		
		try {
			logger.info("selectCountTotal...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = null;
			
			if(search == null){
				psmt = conn.prepareStatement(AdminSql.SELECT_COUNT_TOTAL);
			}else if(search.equals("prodName")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_for_search1All);
				psmt.setString(1, "%"+text+"%");
			}else if(search.equals("prodNo")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_for_search2All);
				psmt.setString(1, "%"+text+"%");
			}else if(search.equals("company")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_for_search3All);
				psmt.setString(1, "%"+text+"%");
			}else if(search.equals("seller")){
				psmt = conn.prepareStatement(AdminSql.select_count_total_for_search4All);
				psmt.setString(1, "%"+text+"%");
			}
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	public List<ProductVO> selectAdminList(String seller, int limiteStart) {
		List<ProductVO> products = new ArrayList<>();
		try {
			logger.debug("selectAdminList...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(AdminSql.SELECT_ADMIN_LIST);
			psmt.setString(1, seller);
			psmt.setInt(2, limiteStart);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getString(1));
				vo.setProdName(rs.getString(4));
				vo.setDescript(rs.getString(5));
				vo.setCompany(rs.getString(6));
				vo.setSeller(rs.getString(7));
				vo.setPrice(rs.getInt(8));
				vo.setDiscount(rs.getInt(9));
				vo.setPoint(rs.getInt(10));
				vo.setDelivery(rs.getString(13));
				vo.setThumb1(rs.getString(17));
				vo.setSellPrice(rs.getInt(33));
				
				products.add(vo);
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return products;
	}
	
	// 삭제
	public int delectProduct(String prodNo) {
		int result = 0;
		try {
			logger.info("delectProduct..");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(AdminSql.DELECT_PRODUCT);
			psmt.setString(1, prodNo);
			result = psmt.executeUpdate();
			conn.close();
			psmt.close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	 //키워드
	 public List<ProductVO> selectProductKeyword(String keyword,int start) {
		List<ProductVO> products = new ArrayList<>();
		try {
			logger.debug("selectProductKeyword...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(AdminSql.select_Product_Keyword);
			psmt.setString(1, "%"+keyword+"%");
			psmt.setString(2, "%"+keyword+"%");
			psmt.setInt(3, start);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				ProductVO product = new ProductVO();
				product.setThumb1(rs.getString(17));
				product.setProdNo(rs.getInt(1));
				product.setProdName(rs.getString(4));
				product.setPrice(rs.getString(8));
				product.setDiscount(rs.getInt(9));
				product.setPoint(rs.getInt(10));
				product.setStock(rs.getInt(11));
				product.setSeller(rs.getString(7));
				product.setHit(rs.getInt(14));
				
				products.add(product);
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return products;
	}
	
	
	
}
