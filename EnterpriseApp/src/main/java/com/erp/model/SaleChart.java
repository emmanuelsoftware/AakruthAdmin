package com.erp.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

public class SaleChart {

	private Date date;
	private BigDecimal amt;

	private SaleChart() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public SaleChart(Date date, BigDecimal amt) {
		super();
		this.date = date;
		this.amt = amt;
	}

	@Override
	public String toString() {
		return "SaleChart [date=" + date + ", amt=" + amt + "]";
	}

}
