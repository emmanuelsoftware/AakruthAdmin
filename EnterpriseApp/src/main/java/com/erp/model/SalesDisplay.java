package com.erp.model;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

public class SalesDisplay {

	int id;
	long cnt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getCnt() {
		return cnt;
	}
	public void setCnt(long cnt) {
		this.cnt = cnt;
	}
	public SalesDisplay() {
	
	}
	public SalesDisplay(int id, long cnt) {
		super();
		this.id = id;
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "SalesDisplay [id=" + id + ", cnt=" + cnt + "]";
	}
}
