package com.aakruth.model;
// Generated Jun 9, 2017 11:46:56 PM by Hibernate Tools 5.2.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * SalTbl generated by hbm2java
 */
@Entity
@Table(name = "sal_tbl", catalog = "aakruthdb")
public class SalTbl implements java.io.Serializable {

	@JsonView(DataTablesOutput.View.class)
	private Integer salId;
	@JsonView(DataTablesOutput.View.class)
	private BillTbl billTbl;
	@JsonView(DataTablesOutput.View.class)
	private PrdTbl prdTbl;
	@JsonView(DataTablesOutput.View.class)
	private BigDecimal amt;
	@JsonView(DataTablesOutput.View.class)
	private int cnt;
	@JsonView(DataTablesOutput.View.class)
	private char sta;
	@JsonView(DataTablesOutput.View.class)
	private int usrId;

	public SalTbl() {
	}

	public SalTbl(BillTbl billTbl, PrdTbl prdTbl, BigDecimal amt, int cnt, char sta, int usrId) {
		this.billTbl = billTbl;
		this.prdTbl = prdTbl;
		this.amt = amt;
		this.cnt = cnt;
		this.sta = sta;
		this.usrId = usrId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SAL_ID", unique = true, nullable = false)
	public Integer getSalId() {
		return this.salId;
	}

	public void setSalId(Integer salId) {
		this.salId = salId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILL_ID", nullable = false)
	public BillTbl getBillTbl() {
		return this.billTbl;
	}

	public void setBillTbl(BillTbl billTbl) {
		this.billTbl = billTbl;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRD_ID", nullable = false)
	public PrdTbl getPrdTbl() {
		return this.prdTbl;
	}

	public void setPrdTbl(PrdTbl prdTbl) {
		this.prdTbl = prdTbl;
	}

	@Column(name = "AMT", nullable = false, precision = 10)
	public BigDecimal getAmt() {
		return this.amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	@Column(name = "CNT", nullable = false)
	public int getCnt() {
		return this.cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Column(name = "STA", nullable = false, length = 1)
	public char getSta() {
		return this.sta;
	}

	public void setSta(char sta) {
		this.sta = sta;
	}

	@Column(name = "usr_id", nullable = false)
	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

}
