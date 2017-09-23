package com.easybusiness.leavemanagement.services.modifyleave;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveActivityResponse;

public interface ModifyLeaveService {

    public ResponseEntity<LeaveActivityResponse> modifyOrCancelLeave(String operationType, String userId, String leaveTypeId,
	    String leaveTransactionId, String leaveStartDate, String leaveEnddate, String locationId, String unitId,
	    String dayType);

}
