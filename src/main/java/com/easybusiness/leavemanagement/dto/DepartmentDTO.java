package com.easybusiness.leavemanagement.dto;

public class DepartmentDTO {

    private long id;

    private OrganizationDTO organization;

    private String deptName;

    public DepartmentDTO() {
	super();
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public OrganizationDTO getOrganization() {
	return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
	this.organization = organization;
    }

    public String getDeptName() {
	return deptName;
    }

    public void setDeptName(String deptName) {
	this.deptName = deptName;
    }

    @Override
    public String toString() {
	return "Department [id=" + id + ", organization=" + organization + ", deptName=" + deptName + "]";
    }

}
