package com.erp.model;
// Generated Jul 17, 2017 12:28:14 AM by Hibernate Tools 5.2.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Branch generated by hbm2java
 */
@Entity
@Table(name = "branch", catalog = "erp")
public class Branch implements java.io.Serializable {

	@JsonView(DataTablesOutput.View.class)
	private int branchId;
	@JsonView(DataTablesOutput.View.class)
	private String branchName;
	@JsonView(DataTablesOutput.View.class)
	private String addrress;
	@JsonView(DataTablesOutput.View.class)
	private int contactNumber;
	@JsonView(DataTablesOutput.View.class)
	private String email;
	@JsonView(DataTablesOutput.View.class)
	private String gstin;
	@JsonView(DataTablesOutput.View.class)
	private String pan;
	@JsonIgnore
	private Set<User> users = new HashSet<User>(0);

	public Branch() {
	}

	public Branch(int branchId, String branchName, String addrress, int contactNumber) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.addrress = addrress;
		this.contactNumber = contactNumber;
	}

	public Branch(int branchId, String branchName, String addrress, int contactNumber, String email, String gstin,
			String pan, Set<User> users) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.addrress = addrress;
		this.contactNumber = contactNumber;
		this.email = email;
		this.gstin = gstin;
		this.pan = pan;
		this.users = users;
	}

	@Id

	@Column(name = "BRANCH_ID", unique = true, nullable = false)
	public int getBranchId() {
		return this.branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Column(name = "BRANCH_NAME", nullable = false, length = 45)
	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "ADDRRESS", nullable = false, length = 45)
	public String getAddrress() {
		return this.addrress;
	}

	public void setAddrress(String addrress) {
		this.addrress = addrress;
	}

	@Column(name = "CONTACT_NUMBER", nullable = false)
	public int getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name = "EMAIL", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "GSTIN", length = 45)
	public String getGstin() {
		return this.gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	@Column(name = "PAN", length = 45)
	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
