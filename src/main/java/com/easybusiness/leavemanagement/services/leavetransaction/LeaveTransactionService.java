package com.easybusiness.leavemanagement.services.leavetransaction;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveTransactionDTO;

public interface LeaveTransactionService {

    public ResponseEntity<LeaveTransactionDTO> findByLeaveTransactionId(String leaveTransactionId);

    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserIdAndLeaveTypeId(String userId,
	    String leaveTypeId);

    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserId(String userId);

    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserName(String userName);

    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserNameAndLeaveTypeId(String userName,
	    String leaveTypeId);

    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserIdAndLeaveTypeIdAndLeaveDate(
	    String userId, String leaveTypeId, String leaveStartDate, String leaveEndDate);
    
    public int countOfLeaveTransactions(
	    String userId, String leaveTypeId, String leaveStartDate, String leaveEndDate);

    public ResponseEntity<List<LeaveTransactionDTO>> findAll();
    
    public ResponseEntity<List<LeaveTransactionDTO>> findByLeaveStatus(String leaveStatus);

    public ResponseEntity<LeaveTransactionDTO> addOrModifyLeaveTransaction(LeaveTransactionDTO leaveTransactionDTO);

    public void deleteLeaveTransaction(String leaveTransactionId);

    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserIdAndLeaveTypeIdAndDateRange(String userId,
	    String leaveTypeId, String leaveStartDate, String leaveEndDate);

}
