package com.aakruth.model;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

public class DealerDisplay {

	@JsonView(DataTablesOutput.View.class)
	int id;
	@JsonView(DataTablesOutput.View.class)
	char type;
	public DealerDisplay(int id, char type) {
		super();
		this.id = id;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	public DealerDisplay() {
	
	}
	@Override
	public String toString() {
		return "DealerDisplay [id=" + id + ", type=" + type + "]";
	}
	
}
