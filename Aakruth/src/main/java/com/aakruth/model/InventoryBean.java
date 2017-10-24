package com.aakruth.model;

public class InventoryBean {

	Integer prdId;
	String prdnme;
	String bldnme;
	long saleCnt;
	long purchaseCnt;
	long damagedCnt;
	long stockOpen;
	long stockClose;

	public String getPrdnme() {
		return prdnme;
	}
	public void setPrdnme(String prdnme) {
		this.prdnme = prdnme;
	}
	public long getStockOpen() {
		return stockOpen;
	}
	public void setStockOpen(long stockOpen) {
		this.stockOpen = stockOpen;
	}
	public long getStockClose() {
		return stockClose;
	}
	public void setStockClose(long stockClose) {
		this.stockClose = stockClose;
	}
	public Integer getPrdId() {
		return prdId;
	}
	public void setPrdId(Integer prdId) {
		this.prdId = prdId;
	}
	public String getBldnme() {
		return bldnme;
	}
	public InventoryBean(Integer prdId, String prdnme, String bldnme, long saleCnt, long purchaseCnt, long damagedCnt, long stockOpen,
			long stockClose) {
		super();
		this.prdnme = prdnme;
		this.prdId = prdId;
		this.bldnme = bldnme;
		this.saleCnt = saleCnt;
		this.purchaseCnt = purchaseCnt;
		this.damagedCnt = damagedCnt;
		this.stockOpen = stockOpen;
		this.stockClose = stockClose;
	}
	public void setBldnme(String bldnme) {
		this.bldnme = bldnme;
	}
	public long getSaleCnt() {
		return saleCnt;
	}
	public void setSaleCnt(long saleCnt) {
		this.saleCnt = saleCnt;
	}
	public long getPurchaseCnt() {
		return purchaseCnt;
	}
	public void setPurchaseCnt(long purchaseCnt) {
		this.purchaseCnt = purchaseCnt;
	}
	public long getDamagedCnt() {
		return damagedCnt;
	}
	public void setDamagedCnt(long damagedCnt) {
		this.damagedCnt = damagedCnt;
	}
	@Override
	public String toString() {
		return "InventoryBean [prdId=" + prdId + ", prdnme=" + prdnme + ", bldnme=" + bldnme + ", saleCnt=" + saleCnt
				+ ", purchaseCnt=" + purchaseCnt + ", damagedCnt=" + damagedCnt + ", stockOpen=" + stockOpen
				+ ", stockClose=" + stockClose + "]";
	}
	
}
