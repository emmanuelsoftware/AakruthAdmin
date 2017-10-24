package com.erp.model;

public class ProductAvail {

	private int prdId;
	private long count;
	public int getPrdId() {
		return prdId;
	}
	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public ProductAvail(int prdId, long count) {
		super();
		this.prdId = prdId;
		this.count = count;
	}
	
}
