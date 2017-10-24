package com.aakruth.model;

import java.math.BigDecimal;
import java.util.Date;

public class Statement {
	String transaction;
	BigDecimal amount;


	public Statement(String transaction, BigDecimal amount) {
		super();
		this.transaction = transaction;
		this.amount = amount;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Ledger [transaction=" + transaction + ", amount=" + amount + "]";
	}

}
