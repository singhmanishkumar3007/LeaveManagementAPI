package com.easybusiness.leavemanagement.services.applyleave;

import static com.easybusiness.leavemanagement.constant.LeaveManagementConstant.USER_HOST_SERVER;

import java.sql.SQLException;
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
import com.easybusiness.leavepersistence.userleaveapply.LeaveApplyRepositoryImpl;

@RestController
@RequestMapping("/easybusiness/leave/")
public class ApplyLeaveServiceImpl implements ApplyLeaveService {

    @Autowired
    private LeaveApplyRepositoryImpl leaveApplyRepositoryImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyLeaveServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "applyleave/{userId}/{leaveTypeId}/{leaveStartDate}/{leaveEnddate}/{locationId}/{unitId}/{dayType}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LeaveActivityResponse> applyLeave(@PathVariable("userId") String userId,
	    @PathVariable("leaveTypeId") String leaveTypeId, @PathVariable("leaveStartDate") String leaveStartDate,
	    @PathVariable("leaveEnddate") String leaveEnddate, @PathVariable("locationId") String locationId,
	    @PathVariable("unitId") String unitId, @PathVariable("dayType") String dayType) {

	String responseMsg;
	HttpStatus httpStatus;
	try {
	    leaveApplyRepositoryImpl.leaveApplyForUser(userId, leaveTypeId,
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveStartDate)).getTime()),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveEnddate)).getTime()), locationId,
		    unitId, dayType);
	    responseMsg = "Leave Successfully Applied";
	    httpStatus = HttpStatus.OK;
	} 
	
	catch (SQLException e) {
	    
	    LOGGER.error("exception while applying leave for user by Id {} , leave apply details {},  {}", userId,
		    userId + "~" + leaveTypeId + "~" + leaveStartDate + "~" + leaveEnddate + "~" + locationId + "~"
			    + unitId + "~" + dayType,
		    e.getMessage());
	    
		responseMsg="Leave Balance is not sufficient  or Issue In Applying Leave";
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	
	catch (Exception e) {
	    LOGGER.error("exception while applying leave for user by Id {} , leave apply details {},  {}", userId,
		    userId + "~" + leaveTypeId + "~" + leaveStartDate + "~" + leaveEnddate + "~" + locationId + "~"
			    + unitId + "~" + dayType,
		    e.getMessage());
	    
	    responseMsg = e.getMessage();
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	LeaveActivityResponse leaveActivityResponse=new LeaveActivityResponse();
	leaveActivityResponse.setResponseCode(httpStatus.toString());
	leaveActivityResponse.setResponseMessage(responseMsg);
	return new ResponseEntity<LeaveActivityResponse>(leaveActivityResponse, httpStatus);
    }

}
