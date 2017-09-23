package com.easybusiness.leavemanagement.dto;

import java.io.Serializable;
import java.sql.Date;

public class LeaveTransactionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private UserDTO user;

    private LeaveMasterDTO leaveMaster;

    private Date appliedOn;

    private UserDTO appliedBy;

    private Date approvedOn;

    private UserDTO approvedBy;

    private Date leaveStartDate;

    private Date canceledOn;

    private Date availedOn;

    private Date raisedOn;

    private Date raisedFor;

    private Date leaveEndDate;

    private StatusDTO status;

    private Long createdBy;

    private Date createdOn;

    private Long modifiedBy;

    private Date modifiedOn;

    private Long leaveNo;

    public LeaveTransactionDTO() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public UserDTO getUser() {
	return user;
    }

    public void setUser(UserDTO user) {
	this.user = user;
    }

    public LeaveMasterDTO getLeaveMaster() {
	return leaveMaster;
    }

    public void setLeaveMaster(LeaveMasterDTO leaveMaster) {
	this.leaveMaster = leaveMaster;
    }

    public Long getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
	this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
	return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
    }

    public Long getModifiedBy() {
	return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
	this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
	return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
	this.modifiedOn = modifiedOn;
    }

    public Date getAppliedOn() {
	return appliedOn;
    }

    public void setAppliedOn(Date appliedOn) {
	this.appliedOn = appliedOn;
    }

    public UserDTO getAppliedBy() {
	return appliedBy;
    }

    public void setAppliedBy(UserDTO appliedBy) {
	this.appliedBy = appliedBy;
    }

    public Date getApprovedOn() {
	return approvedOn;
    }

    public void setApprovedOn(Date approvedOn) {
	this.approvedOn = approvedOn;
    }

    public UserDTO getApprovedBy() {
	return approvedBy;
    }

    public void setApprovedBy(UserDTO approvedBy) {
	this.approvedBy = approvedBy;
    }

    public Date getLeaveStartDate() {
	return leaveStartDate;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
	this.leaveStartDate = leaveStartDate;
    }

    public Date getCanceledOn() {
	return canceledOn;
    }

    public void setCanceledOn(Date canceledOn) {
	this.canceledOn = canceledOn;
    }

    public Date getAvailedOn() {
	return availedOn;
    }

    public void setAvailedOn(Date availedOn) {
	this.availedOn = availedOn;
    }

    public Date getRaisedOn() {
	return raisedOn;
    }

    public void setRaisedOn(Date raisedOn) {
	this.raisedOn = raisedOn;
    }

    public Date getRaisedFor() {
	return raisedFor;
    }

    public void setRaisedFor(Date raisedFor) {
	this.raisedFor = raisedFor;
    }

    public Date getLeaveEndDate() {
	return leaveEndDate;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
	this.leaveEndDate = leaveEndDate;
    }


    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public Long getLeaveNo() {
	return leaveNo;
    }

    public void setLeaveNo(Long leaveNo) {
	this.leaveNo = leaveNo;
    }

    @Override
    public String toString() {
	return "LeaveTransaction [id=" + id + ", user=" + user + ", leaveMaster=" + leaveMaster + ", appliedOn="
		+ appliedOn + ", appliedBy=" + appliedBy + ", approvedOn=" + approvedOn + ", approvedBy=" + approvedBy
		+ ", leaveStartDate=" + leaveStartDate + ", canceledOn=" + canceledOn + ", availedOn=" + availedOn
		+ ", raisedOn=" + raisedOn + ", raisedFor=" + raisedFor + ", leaveEndDate=" + leaveEndDate + ", status="
		+ status + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", modifiedBy=" + modifiedBy
		+ ", modifiedOn=" + modifiedOn + ", leaveNo=" + leaveNo + "]";
    }

}
