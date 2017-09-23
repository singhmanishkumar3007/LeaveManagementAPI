package com.easybusiness.leavemanagement.dto;

public class OrganizationDTO {

    private long id;

    private String orgName;

    private String orgLocation;

    public OrganizationDTO() {
	super();
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getOrgName() {
	return orgName;
    }

    public void setOrgName(String orgName) {
	this.orgName = orgName;
    }

    public String getOrgLocation() {
	return orgLocation;
    }

    public void setOrgLocation(String orgLocation) {
	this.orgLocation = orgLocation;
    }

    @Override
    public String toString() {
	return "Organization [id=" + id + ", orgName=" + orgName + ", orgLocation=" + orgLocation + "]";
    }

}
