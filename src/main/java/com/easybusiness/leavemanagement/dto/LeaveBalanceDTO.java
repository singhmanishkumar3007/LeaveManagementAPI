package com.easybusiness.leavemanagement.dto;

import java.io.Serializable;
import java.sql.Date;

public class LeaveBalanceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private UserDTO user;

    private LeaveMasterDTO leaveMaster;

    private Long openingBalance;

    private Long leaveEarned;

    private Long totalLeave;

    private Long leaveApplied;

    private Long leaveLapsed;

    private Long leaveBalance;

    private LocationMasterDTO locationMaster;

    private Date validFrom;

    private Date validTo;

    private Long createdBy;

    private Date createdOn;

    private Long modifiedBy;

    private Date modifiedOn;

    public LeaveBalanceDTO() {
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

    public Long getOpeningBalance() {
	return openingBalance;
    }

    public void setOpeningBalance(Long openingBalance) {
	this.openingBalance = openingBalance;
    }

    public Long getLeaveEarned() {
	return leaveEarned;
    }

    public void setLeaveEarned(Long leaveEarned) {
	this.leaveEarned = leaveEarned;
    }

    public Long getTotalLeave() {
	return totalLeave;
    }

    public void setTotalLeave(Long totalLeave) {
	this.totalLeave = totalLeave;
    }

    public Long getLeaveApplied() {
	return leaveApplied;
    }

    public void setLeaveApplied(Long leaveApplied) {
	this.leaveApplied = leaveApplied;
    }

    public Long getLeaveLapsed() {
	return leaveLapsed;
    }

    public void setLeaveLapsed(Long leaveLapsed) {
	this.leaveLapsed = leaveLapsed;
    }

    public Long getLeaveBalance() {
	return leaveBalance;
    }

    public void setLeaveBalance(Long leaveBalance) {
	this.leaveBalance = leaveBalance;
    }

    public LocationMasterDTO getLocationMaster() {
	return locationMaster;
    }

    public void setLocationMaster(LocationMasterDTO locationMaster) {
	this.locationMaster = locationMaster;
    }

    public Date getValidFrom() {
	return validFrom;
    }

    public void setValidFrom(Date validFrom) {
	this.validFrom = validFrom;
    }

    public Date getValidTo() {
	return validTo;
    }

    public void setValidTo(Date validTo) {
	this.validTo = validTo;
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
	return "LeaveBalance [id=" + id + ", user=" + user + ", leaveMaster=" + leaveMaster + ", openingBalance="
		+ openingBalance + ", leaveEarned=" + leaveEarned + ", totalLeave=" + totalLeave + ", leaveApplied="
		+ leaveApplied + ", leaveLapsed=" + leaveLapsed + ", leaveBalance=" + leaveBalance + ", locationMaster="
		+ locationMaster + ", validFrom=" + validFrom + ", validTo=" + validTo + ", createdBy=" + createdBy
		+ ", createdOn=" + createdOn + ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn + "]";
    }

}
