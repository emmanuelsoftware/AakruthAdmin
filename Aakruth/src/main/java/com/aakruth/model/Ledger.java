package com.aakruth.model;

import java.math.BigDecimal;
import java.util.Date;

public class Ledger {
	Date date;
	String transaction;
	BigDecimal debit;
	BigDecimal credit;
	BigDecimal vat;

	public Ledger(Date date, String transaction, BigDecimal debit, BigDecimal credit, BigDecimal vat) {
		super();
		this.date = date;
		this.transaction = transaction;
		this.debit = debit;
		this.credit = credit;
		this.vat = vat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	@Override
	public String toString() {
		return "Ledger [date=" + date + ", transaction=" + transaction + ", debit=" + debit + ", credit=" + credit
				+ ", vat=" + vat + "]";
	}

	public int compareTo(Ledger a) {
		if (date.compareTo(a.date) > 0)
			return -1;
		else if (date.compareTo(a.date) < 0)
			return 1;
		else
			return 0;

	}

}
