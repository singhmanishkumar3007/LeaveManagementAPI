package com.easybusiness.leavemanagement.services.leavebalance;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveBalanceDTO;

public interface LeaveBalanceService {

    public ResponseEntity<LeaveBalanceDTO> findByLeaveBalanceId(String leaveBalanceId);

    public ResponseEntity<List<LeaveBalanceDTO>> findLeaveBalanceByUserIdAndLeaveTypeId(String userId,
	    String leaveTypeId);

    public ResponseEntity<List<LeaveBalanceDTO>> findLeaveBalanceByUserId(String userId);

    public ResponseEntity<List<LeaveBalanceDTO>> findLeaveBalanceByUserName(String userName);

    public ResponseEntity<List<LeaveBalanceDTO>> findLeaveBalanceByUserNameAndLeaveTypeId(String userName,
	    String leaveTypeId);

}
