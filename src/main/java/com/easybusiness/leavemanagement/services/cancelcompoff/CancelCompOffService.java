package com.easybusiness.leavemanagement.services.cancelcompoff;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveActivityResponse;

public interface CancelCompOffService {

    public ResponseEntity<LeaveActivityResponse> cancelCompOff(String userId, String leaveStartDate, String leaveEnddate,
	    String locationId, String unitId);

}
