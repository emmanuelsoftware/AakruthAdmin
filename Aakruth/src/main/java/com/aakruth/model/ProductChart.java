package com.aakruth.model;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

public class ProductChart {

	private String name;
	private long cnt;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCnt() {
		return cnt;
	}
	public void setCnt(long cnt) {
		this.cnt = cnt;
	}
	
	public ProductChart() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProductChart [name=" + name + ", cnt=" + cnt + "]";
	}
	public ProductChart(String name, long cnt) {
		super();
		this.name = name;
		this.cnt = cnt;
	}
	
}
