package com.easybusiness.leavemanagement.dto;

import java.io.Serializable;
import java.sql.Date;

public class LeaveMasterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String leaveType;

    private char isCarryForward;

    private Long maxNumInAYear;

    private Long carryForwardLimit;

    private Long validity;

    private String status;

    private Long createdBy;

    private Date createdOn;

    private Long modifiedBy;

    private Date modifiedOn;

    public LeaveMasterDTO() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getLeaveType() {
	return leaveType;
    }

    public void setLeaveType(String leaveType) {
	this.leaveType = leaveType;
    }

    public char getIsCarryForward() {
	return isCarryForward;
    }

    public void setIsCarryForward(char isCarryForward) {
	this.isCarryForward = isCarryForward;
    }

    public Long getMaxNumInAYear() {
	return maxNumInAYear;
    }

    public void setMaxNumInAYear(Long maxNumInAYear) {
	this.maxNumInAYear = maxNumInAYear;
    }

    public Long getCarryForwardLimit() {
	return carryForwardLimit;
    }

    public void setCarryForwardLimit(Long carryForwardLimit) {
	this.carryForwardLimit = carryForwardLimit;
    }

    public Long getValidity() {
	return validity;
    }

    public void setValidity(Long validity) {
	this.validity = validity;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
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

    @Override
    public String toString() {
	return "LeaveMaster [id=" + id + ", leaveType=" + leaveType + ", isCarryForward=" + isCarryForward
		+ ", maxNumInAYear=" + maxNumInAYear + ", carryForwardLimit=" + carryForwardLimit + ", validity="
		+ validity + ", status=" + status + ", createdBy=" + createdBy + ", createdOn=" + createdOn
		+ ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn + "]";
    }

}
