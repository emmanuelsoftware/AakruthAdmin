package com.aakruth.model;
// Generated Jun 9, 2017 11:46:56 PM by Hibernate Tools 5.2.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Dealer generated by hbm2java
 */
@Entity
@Table(name = "dealer", catalog = "aakruthdb")
public class Dealer implements java.io.Serializable {

	@JsonView(DataTablesOutput.View.class)
	private Integer dealerId;
	@JsonView(DataTablesOutput.View.class)
	private String name;
	@JsonView(DataTablesOutput.View.class)
	private String poc;
	@JsonView(DataTablesOutput.View.class)
	private String phnnbr;
	@JsonView(DataTablesOutput.View.class)
	private String email;
	@JsonView(DataTablesOutput.View.class)
	private String adr;
	@JsonView(DataTablesOutput.View.class)
	private Date strdte;
	private Date enddte;
	private char sta;
	private char type;
	private int usrId;
	@JsonIgnore
	private Set<PrdTbl> prdTbls = new HashSet<PrdTbl>(0);
	@JsonIgnore
	private Set<Credit> credits = new HashSet<Credit>(0);
	@JsonIgnore
	private Set<BillTbl> billTbls = new HashSet<BillTbl>(0);
	@JsonIgnore
	private Set<Debit> debits = new HashSet<Debit>(0);

	public Dealer() {
	}

	public Dealer(String name, String poc, String phnnbr, String email, String adr, Date strdte, Date enddte, char sta,
			char type, int usrId) {
		this.name = name;
		this.poc = poc;
		this.phnnbr = phnnbr;
		this.email = email;
		this.adr = adr;
		this.strdte = strdte;
		this.enddte = enddte;
		this.sta = sta;
		this.type = type;
		this.usrId = usrId;
	}

	public Dealer(String name, String poc, String phnnbr, String email, String adr, Date strdte, Date enddte, char sta,
			char type, int usrId, Set<PrdTbl> prdTbls, Set<Credit> credits, Set<BillTbl> billTbls, Set<Debit> debits) {
		this.name = name;
		this.poc = poc;
		this.phnnbr = phnnbr;
		this.email = email;
		this.adr = adr;
		this.strdte = strdte;
		this.enddte = enddte;
		this.sta = sta;
		this.type = type;
		this.usrId = usrId;
		this.prdTbls = prdTbls;
		this.credits = credits;
		this.billTbls = billTbls;
		this.debits = debits;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "DEALER_ID", unique = true, nullable = false)
	public Integer getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "poc", nullable = false, length = 45)
	public String getPoc() {
		return this.poc;
	}

	public void setPoc(String poc) {
		this.poc = poc;
	}

	@Column(name = "phnnbr", nullable = false, length = 45)
	public String getPhnnbr() {
		return this.phnnbr;
	}

	public void setPhnnbr(String phnnbr) {
		this.phnnbr = phnnbr;
	}

	@Column(name = "email", nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "adr", nullable = false, length = 100)
	public String getAdr() {
		return this.adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
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

	@Column(name = "type", nullable = false, length = 1)
	public char getType() {
		return this.type;
	}

	public void setType(char type) {
		this.type = type;
	}

	@Column(name = "usr_id", nullable = false)
	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dealer")
	public Set<PrdTbl> getPrdTbls() {
		return this.prdTbls;
	}

	public void setPrdTbls(Set<PrdTbl> prdTbls) {
		this.prdTbls = prdTbls;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dealer")
	public Set<Credit> getCredits() {
		return this.credits;
	}

	public void setCredits(Set<Credit> credits) {
		this.credits = credits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dealer")
	public Set<BillTbl> getBillTbls() {
		return this.billTbls;
	}

	public void setBillTbls(Set<BillTbl> billTbls) {
		this.billTbls = billTbls;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dealer")
	public Set<Debit> getDebits() {
		return this.debits;
	}

	public void setDebits(Set<Debit> debits) {
		this.debits = debits;
	}

}
