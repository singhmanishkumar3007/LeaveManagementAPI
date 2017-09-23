package com.easybusiness.leavemanagement.services.modifycompoff;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveActivityResponse;

public interface ModifyCompOffService {

    public ResponseEntity<LeaveActivityResponse> modifyCompOff(String userId, String leaveStartDate,
	    String leaveEnddate, String locationId, String unitId, String dayType);

}
