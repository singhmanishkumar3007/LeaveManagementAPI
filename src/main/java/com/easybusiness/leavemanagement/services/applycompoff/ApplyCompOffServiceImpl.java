package com.easybusiness.leavemanagement.services.applycompoff;

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
import com.easybusiness.leavepersistence.usercompoffapply.CompOffApplyRepositoryImpl;

@RestController
@RequestMapping("/easybusiness/leave/")
public class ApplyCompOffServiceImpl implements ApplyCompOffService {

    @Autowired
    private CompOffApplyRepositoryImpl compOffApplyRepositoryImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyCompOffServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "applycompoff/{userId}/{leaveTypeId}/{leaveStartDate}/{leaveEnddate}/{locationId}/{unitId}/{dayType}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LeaveActivityResponse> applyCompOff(@PathVariable("userId") String userId,
	    @PathVariable("leaveTypeId") String leaveTypeId, @PathVariable("leaveStartDate") String leaveStartDate,
	    @PathVariable("leaveEnddate") String leaveEnddate, @PathVariable("locationId") String locationId,
	    @PathVariable("unitId") String unitId, @PathVariable("dayType") String dayType) {

	String responseMsg;
	HttpStatus httpStatus;
	try {
	    compOffApplyRepositoryImpl.compOffApplyForUser(userId, leaveTypeId,
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveStartDate)).getTime()),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveEnddate)).getTime()), locationId,
		    unitId, dayType);
	    responseMsg = "CompOff Successfully Applied";
	    httpStatus = HttpStatus.OK;
	} catch (Exception e) {
	    LOGGER.error("exception while applying CompOff for user by Id {} , leave apply details {},  {}", userId,
		    userId + "~" + leaveTypeId + "~" + leaveStartDate + "~" + leaveEnddate + "~" + locationId + "~"
			    + unitId + "~" + dayType,
		    e.getMessage());
	    responseMsg = "CompOff could not be Applied";
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	LeaveActivityResponse leaveActivityResponse=new LeaveActivityResponse();
	leaveActivityResponse.setResponseCode(httpStatus.toString());
	leaveActivityResponse.setResponseMessage(responseMsg);
	return new ResponseEntity<LeaveActivityResponse>(leaveActivityResponse, httpStatus);
    }

}
