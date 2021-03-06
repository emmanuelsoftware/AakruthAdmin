package com.erp.model;
// Generated Jul 17, 2017 12:28:14 AM by Hibernate Tools 5.2.1.Final

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
 * Task generated by hbm2java
 */
@Entity
@Table(name = "task", catalog = "erp")
public class Task implements java.io.Serializable {

	private Integer taskId;
	private User user;
	private String taskDes;
	private Character sta;
	private Integer audUsrId;
	private Date startdate;
	private Date enddate;
	private String assignRole;
	private Date assignDate;

	public Task() {
	}

	public Task(User user, String taskDes, Character sta, Integer audUsrId, Date startdate, Date enddate,
			String assignRole, Date assignDate) {
		this.user = user;
		this.taskDes = taskDes;
		this.sta = sta;
		this.audUsrId = audUsrId;
		this.startdate = startdate;
		this.enddate = enddate;
		this.assignRole = assignRole;
		this.assignDate = assignDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "TASK_ID", unique = true, nullable = false)
	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSIGN_USR_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "TASK_DES", length = 45)
	public String getTaskDes() {
		return this.taskDes;
	}

	public void setTaskDes(String taskDes) {
		this.taskDes = taskDes;
	}

	@Column(name = "STA", length = 1)
	public Character getSta() {
		return this.sta;
	}

	public void setSta(Character sta) {
		this.sta = sta;
	}

	@Column(name = "AUD_USR_ID")
	public Integer getAudUsrId() {
		return this.audUsrId;
	}

	public void setAudUsrId(Integer audUsrId) {
		this.audUsrId = audUsrId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE", length = 10)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 10)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "ASSIGN_ROLE", length = 10)
	public String getAssignRole() {
		return this.assignRole;
	}

	public void setAssignRole(String assignRole) {
		this.assignRole = assignRole;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ASSIGN_DATE", length = 10)
	public Date getAssignDate() {
		return this.assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

}
