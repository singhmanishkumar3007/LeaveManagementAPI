package com.easybusiness.leavemanagement.dto;

public class DesignationDTO {

    private long id;

    private String desig;

    public DesignationDTO() {
	super();
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getDesig() {
	return desig;
    }

    public void setDesig(String desig) {
	this.desig = desig;
    }

    @Override
    public String toString() {
	return "Designation [id=" + id + ", desig=" + desig + "]";
    }

}
