package com.erp.model;
// Generated Jul 17, 2017 12:28:14 AM by Hibernate Tools 5.2.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Transaction generated by hbm2java
 */
@Entity
@Table(name = "transaction", catalog = "erp")
public class Transaction implements java.io.Serializable {

	private Integer tranId;
	private String detail;
	private char type;
	private Set<Credit> credits = new HashSet<Credit>(0);
	private Set<Debit> debits = new HashSet<Debit>(0);

	public Transaction() {
	}

	public Transaction(String detail, char type) {
		this.detail = detail;
		this.type = type;
	}

	public Transaction(String detail, char type, Set<Credit> credits, Set<Debit> debits) {
		this.detail = detail;
		this.type = type;
		this.credits = credits;
		this.debits = debits;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "tran_Id", unique = true, nullable = false)
	public Integer getTranId() {
		return this.tranId;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	@Column(name = "detail", nullable = false, length = 45)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "type", nullable = false, length = 1)
	public char getType() {
		return this.type;
	}

	public void setType(char type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
	public Set<Credit> getCredits() {
		return this.credits;
	}

	public void setCredits(Set<Credit> credits) {
		this.credits = credits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
	public Set<Debit> getDebits() {
		return this.debits;
	}

	public void setDebits(Set<Debit> debits) {
		this.debits = debits;
	}

}
