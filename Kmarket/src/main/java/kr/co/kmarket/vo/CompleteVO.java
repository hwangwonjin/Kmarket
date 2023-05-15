package kr.co.kmarket.vo;

public class CompleteVO {
	
	private int ordNo;
	private String ordUid;
	private int ordCount;
	private int ordPrice;
	private int ordDiscount;
	private int ordDelivery;
	private int ordSavePoint;
	private int ordUsedPoint;
	private int ordTotPrice;
	private String ordRecipName;
	private String ordRecipHp;
	private String ordRecipZip;
	private String ordRecipAddr1;
	private String ordRecipAddr2;
	private String ordPayment;
	private String ordCompelete;
	private String ordRecipReceiver;
	
	public String getOrdRecipReceiver() {
		return ordRecipReceiver;
	}
	public void setOrdRecipReceiver(String ordRecipReceiver) {
		this.ordRecipReceiver = ordRecipReceiver;
	}
	public int getOrdCount() {
		return ordCount;
	}
	public void setOrdCount(int ordCount) {
		this.ordCount = ordCount;
	}
	public void setOrdCount(String ordCount) {
		this.ordCount = Integer.parseInt(ordCount);
	}
	public int getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = Integer.parseInt(ordNo);
	}
	public String getOrdUid() {
		return ordUid;
	}
	public void setOrdUid(String ordUid) {
		this.ordUid = ordUid;
	}
	public int getOrdPrice() {
		return ordPrice;
	}
	public void setOrdPrice(int ordPrice) {
		this.ordPrice = ordPrice;
	}
	public void setOrdPrice(String ordPrice) {
		this.ordPrice = Integer.parseInt(ordPrice);
	}
	public int getOrdDiscount() {
		return ordDiscount;
	}
	public void setOrdDiscount(int ordDiscount) {
		this.ordDiscount = ordDiscount;
	}
	public void setOrdDiscount(String ordDiscount) {
		this.ordDiscount = Integer.parseInt(ordDiscount);
	}
	public int getOrdDelivery() {
		return ordDelivery;
	}
	public void setOrdDelivery(int ordDelivery) {
		this.ordDelivery = ordDelivery;
	}
	public void setOrdDelivery(String ordDelivery) {
		this.ordDelivery = Integer.parseInt(ordDelivery);
	}
	public int getOrdSavePoint() {
		return ordSavePoint;
	}
	public void setOrdSavePoint(int ordSavePoint) {
		this.ordSavePoint = ordSavePoint;
	}
	public void setOrdSavePoint(String ordSavePoint) {
		this.ordSavePoint = Integer.parseInt(ordSavePoint);
	}
	public int getOrdUsedPoint() {
		return ordUsedPoint;
	}
	public void setOrdUsedPoint(int ordUsedPoint) {
		this.ordUsedPoint = ordUsedPoint;
	}
	public void setOrdUsedPoint(String ordUsedPoint) {
		this.ordUsedPoint = Integer.parseInt(ordUsedPoint);
	}
	public int getOrdTotPrice() {
		return ordTotPrice;
	}
	public void setOrdTotPrice(int ordTotPrice) {
		this.ordTotPrice = ordTotPrice;
	}
	public void setOrdTotPrice(String ordTotPrice) {
		this.ordTotPrice = Integer.parseInt(ordTotPrice);
	}
	public String getOrdRecipName() {
		return ordRecipName;
	}
	public void setOrdRecipName(String ordRecipName) {
		this.ordRecipName = ordRecipName;
	}
	public String getOrdRecipHp() {
		return ordRecipHp;
	}
	public void setOrdRecipHp(String ordRecipHp) {
		this.ordRecipHp = ordRecipHp;
	}
	public String getOrdRecipZip() {
		return ordRecipZip;
	}
	public void setOrdRecipZip(String ordRecipZip) {
		this.ordRecipZip = ordRecipZip;
	}
	public String getOrdRecipAddr1() {
		return ordRecipAddr1;
	}
	public void setOrdRecipAddr1(String ordRecipAddr1) {
		this.ordRecipAddr1 = ordRecipAddr1;
	}
	public String getOrdRecipAddr2() {
		return ordRecipAddr2;
	}
	public void setOrdRecipAddr2(String ordRecipAddr2) {
		this.ordRecipAddr2 = ordRecipAddr2;
	}
	public String getOrdPayment() {
		return ordPayment;
	}
	public void setOrdPayment(String ordPayment) {
		this.ordPayment = ordPayment;
	}
	public String getOrdCompelete() {
		return ordCompelete;
	}
	public void setOrdCompelete(String ordCompelete) {
		this.ordCompelete = ordCompelete;
	}
	
	
	
	
}
