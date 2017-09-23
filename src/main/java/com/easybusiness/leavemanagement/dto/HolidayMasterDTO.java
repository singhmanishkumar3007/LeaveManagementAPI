package com.easybusiness.leavemanagement.dto;

import java.io.Serializable;
import java.sql.Date;

public class HolidayMasterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date holidayDate;

    private String description;

    private String holidayType;

    private Long holidayYear;

    private LocationMasterDTO locationMaster;

    private Long createdBy;

    private Date createdOn;

    private Long modifiedBy;

    private Date modifiedOn;

    public HolidayMasterDTO() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public LocationMasterDTO getLocationMaster() {
	return locationMaster;
    }

    public void setLocationMaster(LocationMasterDTO locationMaster) {
	this.locationMaster = locationMaster;
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

    public Date getHolidayDate() {
	return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
	this.holidayDate = holidayDate;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getHolidayType() {
	return holidayType;
    }

    public void setHolidayType(String holidayType) {
	this.holidayType = holidayType;
    }

    public Long getHolidayYear() {
	return holidayYear;
    }

    public void setHolidayYear(Long holidayYear) {
	this.holidayYear = holidayYear;
    }

    @Override
    public String toString() {
	return "HolidayMaster [id=" + id + ", holidayDate=" + holidayDate + ", description=" + description
		+ ", holidayType=" + holidayType + ", holidayYear=" + holidayYear + ", locationMaster=" + locationMaster
		+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", modifiedBy=" + modifiedBy
		+ ", modifiedOn=" + modifiedOn + "]";
    }

}
