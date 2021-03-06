package com.erp.model;
// Generated Jul 17, 2017 12:28:14 AM by Hibernate Tools 5.2.1.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Debit generated by hbm2java
 */
@Entity
@Table(name = "debit", catalog = "erp")
public class Debit implements java.io.Serializable {

	private Integer debId;
	private Dealer dealer;
	private Transaction transaction;
	private String voucherNo;
	private int typeOfPayment;
	private BigDecimal amt;
	private BigDecimal sgst;
	private BigDecimal igst;
	private BigDecimal cgst;
	private BigDecimal otherTax;
	private String particular;
	private String remarks;
	private Date strdte;
	private Date enddte;
	private char sta;
	private Integer billId;
	private int audUsrId;

	public Debit() {
	}

	public Debit(Transaction transaction, String voucherNo, int typeOfPayment, String particular, String remarks,
			Date strdte, Date enddte, char sta, int audUsrId) {
		this.transaction = transaction;
		this.voucherNo = voucherNo;
		this.typeOfPayment = typeOfPayment;
		this.particular = particular;
		this.remarks = remarks;
		this.strdte = strdte;
		this.enddte = enddte;
		this.sta = sta;
		this.audUsrId = audUsrId;
	}

	public Debit(Dealer dealer, Transaction transaction, String voucherNo, int typeOfPayment, BigDecimal amt,
			BigDecimal sgst, BigDecimal igst, BigDecimal cgst, BigDecimal otherTax, String particular, String remarks,
			Date strdte, Date enddte, char sta, Integer billId, int audUsrId) {
		this.dealer = dealer;
		this.transaction = transaction;
		this.voucherNo = voucherNo;
		this.typeOfPayment = typeOfPayment;
		this.amt = amt;
		this.sgst = sgst;
		this.igst = igst;
		this.cgst = cgst;
		this.otherTax = otherTax;
		this.particular = particular;
		this.remarks = remarks;
		this.strdte = strdte;
		this.enddte = enddte;
		this.sta = sta;
		this.billId = billId;
		this.audUsrId = audUsrId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "deb_Id", unique = true, nullable = false)
	public Integer getDebId() {
		return this.debId;
	}

	public void setDebId(Integer debId) {
		this.debId = debId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dealer_id")
	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type", nullable = false)
	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Column(name = "voucher_no", nullable = false, length = 45)
	public String getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@Column(name = "type_of_payment", nullable = false)
	public int getTypeOfPayment() {
		return this.typeOfPayment;
	}

	public void setTypeOfPayment(int typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	@Column(name = "AMT", precision = 10)
	public BigDecimal getAmt() {
		return this.amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	@Column(name = "SGST", precision = 10)
	public BigDecimal getSgst() {
		return this.sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}

	@Column(name = "IGST", precision = 10)
	public BigDecimal getIgst() {
		return this.igst;
	}

	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}

	@Column(name = "CGST", precision = 10)
	public BigDecimal getCgst() {
		return this.cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	@Column(name = "OTHER_TAX", precision = 10)
	public BigDecimal getOtherTax() {
		return this.otherTax;
	}

	public void setOtherTax(BigDecimal otherTax) {
		this.otherTax = otherTax;
	}

	@Column(name = "particular", nullable = false, length = 100)
	public String getParticular() {
		return this.particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	@Column(name = "remarks", nullable = false, length = 100)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "strdte", nullable = false, length = 10)
	public Date getStrdte() {
		return this.strdte;
	}

	public void setStrdte(Date strdte) {
		this.strdte = strdte;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "enddte", nullable = false, length = 10)
	public Date getEnddte() {
		return this.enddte;
	}

	public void setEnddte(Date enddte) {
		this.enddte = enddte;
	}

	@Column(name = "sta", nullable = false, length = 1)
	public char getSta() {
		return this.sta;
	}

	public void setSta(char sta) {
		this.sta = sta;
	}

	@Column(name = "bill_id")
	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	@Column(name = "aud_usr_id", nullable = false)
	public int getAudUsrId() {
		return this.audUsrId;
	}

	public void setAudUsrId(int audUsrId) {
		this.audUsrId = audUsrId;
	}

}
