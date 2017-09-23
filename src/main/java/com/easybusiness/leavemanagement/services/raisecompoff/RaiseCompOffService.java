package com.easybusiness.leavemanagement.services.raisecompoff;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveActivityResponse;

public interface RaiseCompOffService {

    public ResponseEntity<LeaveActivityResponse> raiseCompOff(String userId, String leaveRaiseDate, String locationId, String unitId);

}
