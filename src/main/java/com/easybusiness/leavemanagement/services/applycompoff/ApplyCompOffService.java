package com.easybusiness.leavemanagement.services.applycompoff;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveActivityResponse;

public interface ApplyCompOffService {

    public ResponseEntity<LeaveActivityResponse> applyCompOff(String userId, String leaveTypeId, String leaveStartDate,
	    String leaveEnddate, String locationId, String unitId, String dayType);

}
