package com.aakruth.model;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

public class PurchaseDisplay {

	int id;
	int cnt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public PurchaseDisplay() {
	
	}
	public PurchaseDisplay(int id, int cnt) {
		super();
		this.id = id;
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "PurchaseDisplay [id=" + id + ", cnt=" + cnt + "]";
	}
	
}
