package com.easybusiness.leavemanagement.dto;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private String firstName;

    private Date fromDate;

    private String lastName;

    private String password;

    private String gender;

    private Date dateOfBirth;

    private String email;

    private String alternateEmail;

    private Long mobile;

    private Date endDate;

    private OrganizationDTO organization;

    private DepartmentDTO department;

    private DesignationDTO designation;

    private String typeOfEmployment;

    private Long isEnabled;

    private Blob userImg;

    private String modifiedBy;

    private Date modifiedOn;

    private String permAddr;

    private String state;

    private String city;

    private String country;

    private String zip;

    private String fatherName;

    private String spouseName;

    private String passport;

    private LocationMasterDTO location;

    private Long unitId;

    public UserDTO() {
	super();
    }
    
    

    public LocationMasterDTO getLocation() {
        return location;
    }



    public void setLocation(LocationMasterDTO location) {
        this.location = location;
    }



    public Long getUnitId() {
        return unitId;
    }



    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }



    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public Date getFromDate() {
	return fromDate;
    }

    public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getAlternateEmail() {
	return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
	this.alternateEmail = alternateEmail;
    }

    public Long getMobile() {
	return mobile;
    }

    public void setMobile(Long mobile) {
	this.mobile = mobile;
    }

    public Date getEndDate() {
	return endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public OrganizationDTO getOrganization() {
	return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
	this.organization = organization;
    }

    public DepartmentDTO getDepartment() {
	return department;
    }

    public void setDepartment(DepartmentDTO department) {
	this.department = department;
    }

    public DesignationDTO getDesignation() {
	return designation;
    }

    public void setDesignation(DesignationDTO designation) {
	this.designation = designation;
    }

    public String getTypeOfEmployment() {
	return typeOfEmployment;
    }

    public void setTypeOfEmployment(String typeOfEmployment) {
	this.typeOfEmployment = typeOfEmployment;
    }

    public Long getIsEnabled() {
	return isEnabled;
    }

    public void setIsEnabled(Long isEnabled) {
	this.isEnabled = isEnabled;
    }

    public Blob getUserImg() {
	return userImg;
    }

    public void setUserImg(Blob userImg) {
	this.userImg = userImg;
    }

    public String getModifiedBy() {
	return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
	return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
	this.modifiedOn = modifiedOn;
    }

    public String getPermAddr() {
	return permAddr;
    }

    public void setPermAddr(String permAddr) {
	this.permAddr = permAddr;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public String getZip() {
	return zip;
    }

    public void setZip(String zip) {
	this.zip = zip;
    }

    public String getFatherName() {
	return fatherName;
    }

    public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
    }

    public String getSpouseName() {
	return spouseName;
    }

    public void setSpouseName(String spouseName) {
	this.spouseName = spouseName;
    }

    public String getPassport() {
	return passport;
    }

    public void setPassport(String passport) {
	this.passport = passport;
    }
    
    



    @Override
    public String toString() {
	return "UserDTO [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", fromDate=" + fromDate
		+ ", lastName=" + lastName + ", password=" + password + ", gender=" + gender + ", dateOfBirth="
		+ dateOfBirth + ", email=" + email + ", alternateEmail=" + alternateEmail + ", mobile=" + mobile
		+ ", endDate=" + endDate + ", organization=" + organization + ", department=" + department
		+ ", designation=" + designation + ", typeOfEmployment=" + typeOfEmployment + ", isEnabled=" + isEnabled
		+ ", userImg=" + userImg + ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn + ", permAddr="
		+ permAddr + ", state=" + state + ", city=" + city + ", country=" + country + ", zip=" + zip
		+ ", fatherName=" + fatherName + ", spouseName=" + spouseName + ", passport=" + passport + ", location="
		+ location + ", unitId=" + unitId + "]";
    }


}
