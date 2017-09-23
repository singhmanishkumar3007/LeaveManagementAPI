package com.easybusiness.leavemanagement.services.modifyleave;

import static com.easybusiness.leavemanagement.constant.LeaveManagementConstant.USER_HOST_SERVER;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybusiness.leavemanagement.dto.LeaveActivityResponse;
import com.easybusiness.leavepersistence.userleavemodifyorcancel.LeaveModifyOrCancelRepositoryImpl;

@RestController
@RequestMapping("/easybusiness/leave/")
public class ModifyLeaveServiceImpl implements ModifyLeaveService {

    @Autowired
    private LeaveModifyOrCancelRepositoryImpl leaveModifyOrCancelRepositoryImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyLeaveServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "modifyorcancelleave/{operationType}/{userId}/{leaveTypeId}/{leaveTransactionId}/{leaveStartDate}/{leaveEnddate}/{locationId}/{unitId}/{dayType}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LeaveActivityResponse> modifyOrCancelLeave(
	    @PathVariable("operationType") String operationType, @PathVariable("userId") String userId,
	    @PathVariable("leaveTypeId") String leaveTypeId,
	    @PathVariable("leaveTransactionId") String leaveTransactionId,
	    @PathVariable("leaveStartDate") String leaveStartDate, @PathVariable("leaveEnddate") String leaveEnddate,
	    @PathVariable("locationId") String locationId, @PathVariable("unitId") String unitId,
	    @PathVariable("dayType") String dayType) {

	String responseMsg;
	HttpStatus httpStatus;
	try {
	    leaveModifyOrCancelRepositoryImpl.leaveModifyOrCancelForUser(operationType, userId, leaveTypeId,
		    leaveTransactionId,
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveStartDate)).getTime()),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveEnddate)).getTime()), locationId,
		    unitId, dayType);
	    responseMsg = operationType+ "Leave Successfully Done" ;
	    httpStatus = HttpStatus.OK;
	} catch (Exception e) {
	    LOGGER.error(
		    "exception while " + operationType + "ing"
			    + " leave for user by Id {} , leave apply details {},  {}",
		    userId, userId + "~" + leaveTypeId + "~" + leaveStartDate + "~" + leaveEnddate + "~" + locationId
			    + "~" + unitId + "~" + dayType,
		    e.getMessage());
	    responseMsg = operationType+" Leave could not be  Done" ;
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	LeaveActivityResponse leaveActivityResponse = new LeaveActivityResponse();
	leaveActivityResponse.setResponseCode(httpStatus.toString());
	leaveActivityResponse.setResponseMessage(responseMsg);
	return new ResponseEntity<LeaveActivityResponse>(leaveActivityResponse, httpStatus);
    }

}
