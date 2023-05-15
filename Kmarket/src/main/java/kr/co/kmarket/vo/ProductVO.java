package kr.co.kmarket.vo;

public class ProductVO {

	private String prodCate1;
	private String prodCate2;
	private String prodName;
	private String descript;
	private String company;
	private String seller;
	private int price;
	private int discount;
	private int point;
	private int stock;
	private int delivery;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String detail;
	private String status;
	private String duty;
	private String receipt;
	private String bizType;
	private String origin;
	private String regip;
	private int sellPrice;
	private int prodNo;
	private int prodnum;
	private int count;
	private int total;
	private int hit;
	private String c1Name;
	private String c2Name;
	private int level;
	private int score;
	private String uid;
	private int cartNo;
	private int totalcount;
	private int costPrice;
	private int totalSalePrice;
	private int totalDelivery;
	private int totalPoint;
	private int totalPrice;
	private String content;
	private int rating;
	private String rdate;
	
	// 추가
	
	public String getRdate() {
		return rdate;
	}
	
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public void setRating(String rating) {
		this.rating = Integer.parseInt(rating);
	}
	
	
	public int getCartNo() {
		return cartNo;
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
	
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = Integer.parseInt(totalPoint);
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	
	public void setTotalcount(String totalcount) {
		this.totalcount = Integer.parseInt(totalcount);
	}

	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	
	public void setCostPrice(String costPrice) {
		this.costPrice = Integer.parseInt(costPrice);
	}

	public int getTotalSalePrice() {
		return totalSalePrice;
	}

	public void setTotalSalePrice(int totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}
	
	public void setTotalSalePrice(String totalSalePrice) {
		this.totalSalePrice = Integer.parseInt(totalSalePrice);
	}

	public int getTotalDelivery() {
		return totalDelivery;
	}

	public void setTotalDelivery(int totalDelivery) {
		this.totalDelivery = totalDelivery;
	}
	
	public void setTotalDelivery(String totalDelivery) {
		this.totalDelivery = Integer.parseInt(totalDelivery);
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = Integer.parseInt(totalPrice);
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	
	public void setCartNo(String cartNo) {
		this.cartNo = Integer.parseInt(cartNo);
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setScore(String score) {
		this.score = Integer.parseInt(score);
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setLevel(String level) {
		this.level = Integer.parseInt(level);
	}
	
	public String getC1Name() {
		return c1Name;
	}
	
	public void setC1Name(String c1Name) {
		this.c1Name = c1Name;
	}
	
	public String getC2Name() {
		return c2Name;
	}
	
	public void setC2Name(String c2Name) {
		this.c2Name = c2Name;
	}
  
  public int getProdnum() {
		return prodnum;
	}

	public void setProdnum(int prodnum) {
		this.prodnum = prodnum;
	}
	
	public int getProdNo() {
		return prodNo;
	}
	
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	
	public void setProdNo(String prodNo) {
		this.prodNo = Integer.parseInt(prodNo);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public int getSellPrice() {
		return sellPrice;
	}
	
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public void setSellPrice(String sellPrice) {
		this.sellPrice = Integer.parseInt(sellPrice);
	}
	
	public String getSeller() {
		return seller;
	}
	
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	public String getRegip() {
		return regip;
	}
	
	public void setRegip(String regip) {
		this.regip = regip;
	}
	
	public String getProdCate1() {
		return prodCate1;
	}
	public void setProdCate1(String prodCate1) {
		this.prodCate1 = prodCate1;
	}
	public String getProdCate2() {
		return prodCate2;
	}
	public void setProdCate2(String prodCate2) {
		this.prodCate2 = prodCate2;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public void setDiscount(String discount) {
		this.discount = Integer.parseInt(discount);
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setPoint(String point) {
		this.point = Integer.parseInt(point);
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setStock(String stock) {
		this.stock = Integer.parseInt(stock);
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = Integer.parseInt(delivery);
	}
	public String getThumb1() {
		return thumb1;
	}
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	public String getThumb2() {
		return thumb2;
	}
	public void setThumb2(String thumb2) {
		this.thumb2 = thumb2;
	}
	public String getThumb3() {
		return thumb3;
	}
	public void setThumb3(String thumb3) {
		this.thumb3 = thumb3;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	
	
}
