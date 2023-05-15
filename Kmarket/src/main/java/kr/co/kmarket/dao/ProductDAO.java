package kr.co.kmarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBCP;
import kr.co.kmarket.db.Indexsql;
import kr.co.kmarket.db.ProductSql;
import kr.co.kmarket.vo.CategoryVO;
import kr.co.kmarket.vo.CompleteVO;
import kr.co.kmarket.vo.ProductVO;

public class ProductDAO {
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 상품 입력
	public int insertProduct(ProductVO vo) {
		
		int prodNo = 0;
		
		try {
			logger.debug("insertProduct...");
			Connection conn = DBCP.getConnection();
			
			// 트랙잭션 시작
			conn.setAutoCommit(false);
			PreparedStatement psmt = conn.prepareStatement(ProductSql.insertProduct);
			Statement stmt = conn.createStatement();
			psmt.setString(1, vo.getProdCate1());
			psmt.setString(2, vo.getProdCate2());
			psmt.setString(3, vo.getProdName());
			psmt.setString(4, vo.getDescript());
			psmt.setString(5, vo.getCompany());
			psmt.setInt(6, vo.getPrice());
			psmt.setInt(7, vo.getDiscount());
			psmt.setInt(8, vo.getPoint());
			psmt.setInt(9, vo.getStock());
			psmt.setInt(10, vo.getDelivery());
			psmt.setString(11, vo.getStatus());
			psmt.setString(12, vo.getDuty());
			psmt.setString(13, vo.getReceipt());
			psmt.setString(14, vo.getBizType());
			psmt.setString(15, vo.getOrigin());
			psmt.setString(16, vo.getRegip());
			psmt.setString(17, vo.getThumb1());
			psmt.setString(18, vo.getThumb2());
			psmt.setString(19, vo.getThumb3());
			psmt.setString(20, vo.getDetail());
			psmt.setString(21, vo.getSeller());
			psmt.setInt(22, vo.getSellPrice());
			
			psmt.executeUpdate();                                         // INSERT 이후
			ResultSet rs = stmt.executeQuery(ProductSql.selectProdNo);    // SELECT 해야 INSERT한 prodNo 찾기 가능 반대로하면 틀린 prodNO
			conn.commit();
			// 트랜잭션 종료
			
			if(rs.next()) {
				prodNo = rs.getInt(1);
			}
			
			conn.close();
			psmt.close();
			stmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return prodNo;
		
	}

	//상품 항목 리스트 조회 및 조건에 따른 리스트 조회
	public List<ProductVO> selectProducts(int limiteStart, String cate1, String cate2, String sort) {
		
		List<ProductVO> products = new ArrayList<>();
		
		try {
			logger.debug("selectProduct...");
			Connection conn = DBCP.getConnection();
			
			PreparedStatement psmt = null;
			
			if(sort == null) {
				psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCTS);
				psmt.setString(1, cate1);
				psmt.setString(2, cate2);
				psmt.setInt(3, limiteStart);
			}else if(sort.equals("sold")) {
				psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCTS_SOLD);
				psmt.setString(1, cate1);
				psmt.setString(2, cate2);
				psmt.setInt(3, limiteStart);
			}else if(sort.equals("ascSold")) {
				psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCTS_SELLPRICE_DOWN);
				psmt.setString(1, cate1);
				psmt.setString(2, cate2);
				psmt.setInt(3, limiteStart);
			}else if(sort.equals("descSold")) {
				psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCTS_SELLPRICE_up);
				psmt.setString(1, cate1);
				psmt.setString(2, cate2);
				psmt.setInt(3, limiteStart);
			}else if(sort.equals("score")) {
				psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCTS_SELLPRICE_SCORE);
				psmt.setString(1, cate1);
				psmt.setString(2, cate2);
				psmt.setInt(3, limiteStart);
			}else if(sort.equals("review")) {
				psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCTS_SELLPRICE_REVIEW);
				psmt.setString(1, cate1);
				psmt.setString(2, cate2);
				psmt.setInt(3, limiteStart);
			}else if(sort.equals("rdate")) {
				psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCTS_SELLPRICE_RDATE);
				psmt.setString(1, cate1);
				psmt.setString(2, cate2);
				psmt.setInt(3, limiteStart);
			}
			
			logger.info("sort : "+sort);
			logger.info("selctCate1 : "+cate1);
			logger.info("selctCate2 : "+cate2);
			logger.info("selectlimit : "+limiteStart);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
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
				vo.setScore(rs.getString(15));
				vo.setThumb1(rs.getString(17));
				vo.setSellPrice(rs.getInt(33));
				vo.setLevel(rs.getString(34));
				
				products.add(vo);
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("products size : " +products.size());
		return products;
	}
	
	//단일 상품 조회
	public ProductVO selectProduct(String prodNo) {
		
		ProductVO vo = null;
		
		try {
			logger.debug("selectProduct...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCT);
			psmt.setString(1, prodNo);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new ProductVO();
				vo.setProdNo(rs.getString(1));
				vo.setProdCate1(rs.getString(2));
				vo.setProdCate2(rs.getString(3));
				vo.setProdName(rs.getString(4));
				vo.setDescript(rs.getString(5));
				vo.setCompany(rs.getString(6));
				vo.setPrice(rs.getString(8));
				vo.setDiscount(rs.getString(9));
				vo.setPoint(rs.getString(10));
				vo.setDelivery(rs.getString(13));
				vo.setThumb1(rs.getString(17));
				vo.setThumb2(rs.getString(18));
				vo.setThumb3(rs.getString(19));
				vo.setDetail(rs.getString(20));
				vo.setStatus(rs.getString(21));
				vo.setDuty(rs.getString(22));
				vo.setReceipt(rs.getString(23));
				vo.setBizType(rs.getString(24));
				vo.setOrigin(rs.getString(25));
				vo.setSellPrice(rs.getString(33));
				vo.setC1Name(rs.getString(34));
				vo.setC2Name(rs.getString(35));
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return vo;
	}
	
	//단일 상품 리뷰 조회
	public List<ProductVO> selectReview(String prodNo) {
		
		List<ProductVO> products = new ArrayList<>();
		
		try {
			logger.debug("selectReview...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.SELECT_REVIEW);
			psmt.setString(1, prodNo);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setUid(rs.getString(4));
				vo.setRating(rs.getInt(5));
				vo.setRdate(rs.getString(7));
				vo.setProdName(rs.getString(8));
				
				products.add(vo);
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("products : "+products);
		
		return products;
	}
	
	// order 클릭 시 조회
	public List<ProductVO> selectOrderProduct(String prodNo, int count) {
		
		List<ProductVO> products = new ArrayList<>();
		ProductVO vo = null;
		
		try {
			logger.debug("selectProduct...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCT);
			psmt.setString(1, prodNo);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new ProductVO();
				vo.setProdNo(rs.getString(1));
				vo.setProdCate1(rs.getString(2));
				vo.setProdCate2(rs.getString(3));
				vo.setProdName(rs.getString(4));
				vo.setDescript(rs.getString(5));
				vo.setCompany(rs.getString(6));
				vo.setPrice(rs.getInt(8)*count);
				vo.setDiscount(rs.getString(9));
				vo.setPoint(rs.getString(10));
				vo.setDelivery(rs.getString(13));
				vo.setThumb1(rs.getString(17));
				vo.setThumb2(rs.getString(18));
				vo.setDetail(rs.getString(20));
				vo.setStatus(rs.getString(21));
				vo.setDuty(rs.getString(22));
				vo.setReceipt(rs.getString(23));
				vo.setBizType(rs.getString(24));
				vo.setOrigin(rs.getString(25));
				vo.setSellPrice(rs.getInt(33));
				vo.setC1Name(rs.getString(34));
				vo.setC2Name(rs.getString(35));
				vo.setTotal(rs.getInt(33)*count+rs.getInt(13));
				vo.setCount(count);
				
				products.add(vo);
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return products;
	}
	
	//order 상품 계산
	public ProductVO selectOrderTotal(String prodNo, int coun) {
		
		ProductVO vo = null;
		
		try {
			logger.debug("selectOrderTotal...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.SELECT_ORDER_TOTAL);
			psmt.setString(1, prodNo);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new ProductVO();
				vo.setPrice(rs.getInt(1)*coun);
				vo.setDiscount((rs.getInt(1)*coun)-(rs.getInt(4)*coun));
				vo.setDelivery(rs.getInt(3));
				vo.setTotal(rs.getInt(4)*coun+rs.getInt(3));
				vo.setCount(1);
				vo.setPoint(rs.getInt(5)*coun);
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return vo;
	}
	
	//상품 총 갯수 조회
	public int selectCountTotal(String cate1, String cate2) {
		
		int total = 0;
		
		try {
			logger.info("selectCountTotal...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			
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

	//상품 네비게이션
	public CategoryVO selectCate(String cate1, String cate2) {
		
		CategoryVO vo = null;
		
		try {
			logger.debug("seleceCate...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.SELECT_CATE);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new CategoryVO();
				vo.setCate1(rs.getInt(1));
				vo.setC1Name(rs.getString(2));
				vo.setCate2(rs.getInt(4));
				vo.setC2Name(rs.getString(5));
			}
			
			psmt.close();
			conn.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	//장바구니 등록
	public int updateCart(String uid, String count, String prodNo) {
		int result = 0;
		try {
			logger.debug("updateCart....");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.UPDATE_PRODUCT_CART);
			psmt.setString(1, uid);
			psmt.setString(2, count);
			psmt.setString(3, count);
			psmt.setString(4, count);
			psmt.setString(5, prodNo);
			
			
			result = psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
	//장바구니 목록
	public List<ProductVO> selectProductCart(String uid) {
		
		List<ProductVO> carts = new ArrayList<>();
		
		try {
			logger.debug("selectProductCart...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.SELECT_PRODUCTS_CART);
			psmt.setString(1, uid);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setThumb1(rs.getString(1));
				vo.setProdName(rs.getString(2));
				vo.setDescript(rs.getString(3));
				vo.setCount(rs.getInt(4));
				vo.setPrice(rs.getString(5));
				vo.setDiscount(rs.getString(6));
				vo.setPoint(rs.getString(7));
				vo.setDelivery(rs.getString(8));
				vo.setSellPrice(rs.getString(9));
				vo.setProdNo(rs.getString(10));
				vo.setTotal(rs.getInt(11));
				vo.setCartNo(rs.getInt(12));
				
				carts.add(vo);
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return carts;
	}
	
	//장바구니 삭제
	public int deleteCart(List<String> totalNo) {
		int result = 0;
		
		// List의 크기 구하기
		int length = totalNo.size();
		
		String sql = "DELETE FROM `km_product_cart` WHERE `cartNo` = ?";
		
		// List의 크기에 맞춰서 조건문 추가
		for(int i = 1; i<length; i++) {
			sql += " OR `cartNo`=?";
		}
		
		logger.debug(sql);
		
		try {
			logger.debug("deleteCart...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt =conn.prepareStatement(sql);
			// List의 크기에 맞춰서 setString 추가
			for(int k = 0; k<length; k++) {
				// setString은 (1, xx)이고 List의 값은 0부터 시작
				// 따라서 k = 0부터 시작 (k+1, totalNo.get(k)) 로 설정
				psmt.setString(k+1, totalNo.get(k));
			}
			
			// !중요! 이때 result는 sql 실행 횟수를 가져옴
			// List의 크기가 3이라면 3번 지워서 result = 3이 나옴.
			// 더 자세한건 Cart.js 참조
			result = psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("d : "+result);
		
		return result;
	}
	
	//최종가격 조회
	public ProductVO selectTotalPrice(String uid) {
		
		logger.debug("dao uid : "+uid);
		ProductVO vo = null;
		
		try {
			logger.debug("selectTotalPrice...");
			Connection conn = DBCP.getConnection();
			PreparedStatement psmt = conn.prepareStatement(ProductSql.SELECT_TOTAL_PRICE);
			psmt.setString(1, uid);
			
			
			logger.debug("psmt : "+psmt);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
			vo = new ProductVO();
			vo.setTotalcount(rs.getInt(1));
			vo.setCostPrice(rs.getInt(2));
			vo.setTotalSalePrice(rs.getInt(3));
			vo.setTotalDelivery(rs.getInt(4));
			vo.setTotalPoint(rs.getInt(5));
			vo.setTotalPrice(rs.getInt(6));
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("vo size : " +vo);
		
		return vo;
	}
	
	//장바구니 선택 항목 조회 2개 이상
	public List<ProductVO> selectCarts(String[] cartNo) {
		
		// 가져온 cartNo의 배열 길이를 구함
		int length = cartNo.length;
		
		// 쿼리문
		String sql = "SELECT a.*, b.`prodName`, b.`descript`, b.`thumb1` FROM `km_product_cart` AS a JOIN `km_product` ";
			  sql += "AS b ON a.`prodNo` = b.`prodNo` WHERE `cartNo`=?";
			  
		List<ProductVO> products = new ArrayList<>();
		
		logger.debug("length : "+length);
		try {
			logger.debug("selectCart...");
			Connection conn = DBCP.getConnection();
			
			// cartNo의 배열 길이만큼 OR `cartNo`=? 를 추가함 최초 WHERE `cartNo`=? 가 있음으로 i= 1부터 시작함
			for(int i = 1; i<length; i++) {
				sql += " OR `cartNo`=?";
			}
			
			// 콘솔창에서 sql문이 어떻게 만들어지는지 확인하면 좋을듯!
			logger.debug("sql : "+sql);
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			// cartNo의 배열 길이만큼 매개변수 값을 대입함
			// 배열의 첫번째 값은 cartNo[0]임 따라서 k=0부터 시작하고, 매개변수 대입을 (k+1, cartNo[k]) 로 하면 (1, cartNo[0])가 됨
			for(int k = 0; k<length; k++) {
				psmt.setString(k+1, cartNo[k]);
			}
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setCartNo(rs.getString(1));
				vo.setUid(rs.getString(2));
				vo.setProdNo(rs.getString(3));
				vo.setCount(rs.getString(4));
				vo.setPrice(rs.getString(5));
				vo.setDiscount(rs.getString(6));
				vo.setPoint(rs.getString(7));
				vo.setDelivery(rs.getString(8));
				vo.setTotal(rs.getInt(9));
				vo.setProdName(rs.getString(11));
				vo.setDescript(rs.getString(12));
				vo.setThumb1(rs.getString(13));
				
				products.add(vo);
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return products;
	}
	
	//장바구니 선택 항목 조회 1개
	public ProductVO selectCart(String cartNo) {
		
		ProductVO vo = null;
		
		String sql = "SELECT a.*, b.`prodName`, b.`descript`, b.`thumb1` FROM `km_product_cart` AS a JOIN `km_product` ";
			  sql += "AS b ON a.`prodNo` = b.`prodNo` WHERE `cartNo`=?";
		
		try {
			logger.debug("selectCart...");
			Connection conn = DBCP.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, cartNo);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new ProductVO();
				vo.setCartNo(rs.getString(1));
				vo.setUid(rs.getString(2));
				vo.setProdNo(rs.getString(3));
				vo.setCount(rs.getString(4));
				vo.setPrice(rs.getString(5));
				vo.setDiscount(rs.getString(6));
				vo.setPoint(rs.getString(7));
				vo.setDelivery(rs.getString(8));
				vo.setTotal(rs.getInt(9));
				vo.setProdName(rs.getString(11));
				vo.setDescript(rs.getString(12));
				vo.setThumb1(rs.getString(13));
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return vo;
	}
	
	//장바구니 선택 항복 계산
	public ProductVO selectSumCart(String[] cartNo) {
		int length = cartNo.length;
		String sql = "SELECT COUNT(`count`), SUM(`price`), SUM(`price`)-(SUM(`total`)-SUM(`delivery`)), SUM(`delivery`), SUM(`total`), SUM(`point`) FROM `km_product_cart` ";
			  sql += "WHERE `cartNo`=?";
			  
		ProductVO vo = null;
		
		logger.debug("length : "+length);
		try {
			logger.debug("selectCart...");
			Connection conn = DBCP.getConnection();
			
			for(int i = 1; i<length; i++) {
				sql += " OR `cartNo`=?";
			}
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			for(int k = 0; k<length; k++) {
				psmt.setString(k+1, cartNo[k]);
			}
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new ProductVO();
				vo.setCount(rs.getString(1));
				vo.setPrice(rs.getString(2));
				vo.setDiscount(rs.getString(3));
				vo.setDelivery(rs.getString(4));
				vo.setTotal(rs.getInt(5));
				vo.setPoint(rs.getString(6));
			}
			
			conn.close();
			psmt.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return vo;
	}
	
	//장바구니 최종 항목 order 등록
	public CompleteVO insertOrder(CompleteVO vo) {
		
		CompleteVO order = null;
		
		try {
			logger.debug("insertOrder...");
			Connection conn = DBCP.getConnection();
			
			conn.setAutoCommit(false);
			
			PreparedStatement psmt = conn.prepareStatement(ProductSql.INSERT_ORDER);
			psmt.setInt(1, vo.getOrdNo());
			psmt.setString(2, vo.getOrdUid());
			psmt.setInt(3, vo.getOrdCount());
			psmt.setInt(4, vo.getOrdPrice());
			psmt.setInt(5, vo.getOrdDiscount());
			psmt.setInt(6, vo.getOrdDelivery());
			psmt.setInt(7, vo.getOrdSavePoint());
			psmt.setInt(8, vo.getOrdUsedPoint());
			psmt.setInt(9, vo.getOrdTotPrice());
			psmt.setString(10, vo.getOrdRecipName());
			psmt.setString(11, vo.getOrdRecipHp());
			psmt.setString(12, vo.getOrdRecipZip());
			psmt.setString(13, vo.getOrdRecipAddr1());
			psmt.setString(14, vo.getOrdRecipAddr2());
			psmt.setString(15, vo.getOrdPayment());
			psmt.setString(16, vo.getOrdCompelete());
			psmt.setString(17, vo.getOrdRecipReceiver());
			
			psmt.executeUpdate();
			
			PreparedStatement psmt2 = conn.prepareStatement(ProductSql.SELECT_ORDER);
			psmt2.setInt(1, vo.getOrdNo());
			ResultSet rs = psmt2.executeQuery();
			
			conn.commit();
			
			if(rs.next()) {
				order = new CompleteVO();
				order.setOrdNo(rs.getString(1));
				order.setOrdPrice(rs.getString(4));
				order.setOrdDiscount(rs.getString(5));
				order.setOrdDelivery(rs.getString(6));
				order.setOrdTotPrice(rs.getString(9));
				order.setOrdRecipName(rs.getString(10));
				order.setOrdRecipHp(rs.getString(11));
				order.setOrdRecipAddr1(rs.getString(13));
				order.setOrdRecipAddr2(rs.getString(14));
				order.setOrdPayment(rs.getString(15));
				order.setOrdRecipReceiver(rs.getString(18));
			}
			
			conn.close();
			psmt.close();
			psmt2.close();
			rs.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
		
		return order;
		
	}
	
	//장바구니 선택항목 order_item insert
	public void insertSelectDeleteCartPoint(String[] cartNo, int ordNo, String uid, String ordsavePoint, String currentPoint, String ordusedPoint) {
		
		String sql = "INSERT INTO `km_product_order_item` (`prodNo`, `count`, `price`, `discount`, `point`, `delivery`, `total`, `orderNo`) ";
			  sql += "SELECT `prodNo`, `count`, `price`, `discount`, `point` , `delivery`, `total`, ? FROM `km_product_cart` ";
			  sql += "WHERE cartNo = ?";
		
		int length = cartNo.length;
		
		for(int i = 1; i<length; i++) {
			sql += " OR `cartNo`=?";
		}
			  
		try {
			logger.debug("public void insertSelectCartPoint...");
			Connection conn = DBCP.getConnection();
			
			conn.setAutoCommit(false);
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ordNo);
			for(int k = 0; k<length; k++) {
				psmt.setString(k+2, cartNo[k]);
			}
			
			PreparedStatement psmt2 = conn.prepareStatement(ProductSql.INSERT_POINT);
			psmt2.setString(1, uid);
			psmt2.setInt(2, ordNo);
			psmt2.setString(3, ordsavePoint);
			
			PreparedStatement psmt3 = conn.prepareStatement(ProductSql.UPDATE_POINT);
			psmt3.setInt(1, Integer.parseInt(currentPoint)-Integer.parseInt(ordusedPoint)+Integer.parseInt(ordsavePoint));
			psmt3.setString(2, uid);
			
			PreparedStatement psmt4 = conn.prepareStatement(ProductSql.DELETE_CART);
			psmt4.setString(1, uid);
			
			psmt.executeUpdate();
			psmt4.executeUpdate();
			psmt2.executeUpdate();
			psmt3.executeUpdate();
			conn.commit();
			
			conn.close();
			psmt.close();
			psmt2.close();
			psmt3.close();
			psmt4.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	//장바구니 선택항목 order_item insert
	public void insertOneSelectDeleteCartPoint(String prodNo, CompleteVO vo, int ordNo, String uid, String ordsavePoint, String currentPoint, String ordusedPoint) {
		
		try {
			logger.debug("public void insertSelectCartPoint...");
			Connection conn = DBCP.getConnection();
			
			conn.setAutoCommit(false);
			PreparedStatement psmt = conn.prepareStatement(ProductSql.INSERT_ORDER_ITEM);
			psmt.setInt(1, vo.getOrdNo());
			psmt.setString(2, prodNo);
			psmt.setInt(3, vo.getOrdCount());
			psmt.setInt(4, vo.getOrdPrice());
			psmt.setInt(5, vo.getOrdDiscount());
			psmt.setInt(6, vo.getOrdSavePoint());
			psmt.setInt(7, vo.getOrdDelivery());
			psmt.setInt(8, vo.getOrdTotPrice());
			
			PreparedStatement psmt2 = conn.prepareStatement(ProductSql.INSERT_POINT);
			psmt2.setString(1, uid);
			psmt2.setInt(2, ordNo);
			psmt2.setString(3, ordsavePoint);
			
			PreparedStatement psmt3 = conn.prepareStatement(ProductSql.UPDATE_POINT);
			psmt3.setInt(1, Integer.parseInt(currentPoint)-Integer.parseInt(ordusedPoint)+Integer.parseInt(ordsavePoint));
			psmt3.setString(2, uid);
			
			PreparedStatement psmt4 = conn.prepareStatement(ProductSql.DELETE_CART);
			psmt4.setString(1, uid);
			
			psmt.executeUpdate();
			psmt4.executeUpdate();
			psmt2.executeUpdate();
			psmt3.executeUpdate();
			conn.commit();
			
			conn.close();
			psmt.close();
			psmt2.close();
			psmt3.close();
			psmt4.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
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
