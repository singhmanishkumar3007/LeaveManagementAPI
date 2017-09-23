package com.easybusiness.leavemanagement.services.applyleave;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveActivityResponse;

public interface ApplyLeaveService {

    public ResponseEntity<LeaveActivityResponse> applyLeave(String userId, String leaveTypeId, String leaveStartDate,
	    String leaveEnddate, String locationId, String unitId, String dayType);

}
