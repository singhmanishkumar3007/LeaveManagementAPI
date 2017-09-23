package com.easybusiness.leavemanagement.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS_MASTER")
public class StatusDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STATUS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String statusId;

    @Column(name = "STATUS_NAME")
    private String statusName;

    public StatusDTO() {
	super();
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
	return "Status [statusId=" + statusId + ", statusName=" + statusName + "]";
    }
    
    

}
