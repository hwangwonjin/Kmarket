package kr.co.kmarket.db;

public class Indexsql {
	
	public static final String SELECT_CATE = "SELECT * FROM `km_product_cate1` AS a JOIN `km_product_cate2` AS b ON a.cate1 = b.cate1 WHERE a.`cate1`=?";
	
	public static final String SELECT_CATEGORY = "SELECT * FROM `km_product_cate1` AS a JOIN `km_product_cate2` AS b ON a.cate1 = b.cate1";
	
	public static final String SELECT_CATE1 = "SELECT * FROM `km_product_cate1`";
	
	public static final String SELECT_BEST = "(SELECT * FROM `km_product` ORDER BY `sold` DESC LIMIT 0,5) "
											+ "UNION "
											+ "(SELECT * FROM `km_product` ORDER BY `hit` DESC LIMIT 0,8) "
											+ "UNION "
											+ "(SELECT * FROM `km_product` ORDER BY `score` DESC LIMIT 0,8) "
											+ "UNION "
											+ "(SELECT * FROM `km_product` ORDER BY `prodNo` DESC LIMIT 0,8) "
											+ "UNION "
											+ "(SELECT * FROM `km_product` ORDER BY `discount` DESC LIMIT 0,8)";
	
	public static final String SELECT_BEST1 = "SELECT * FROM `km_product` ORDER BY `sold` DESC LIMIT 0,1";
}
