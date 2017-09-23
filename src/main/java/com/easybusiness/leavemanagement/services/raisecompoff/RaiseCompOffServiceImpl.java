package com.easybusiness.leavemanagement.services.raisecompoff;

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
import com.easybusiness.leavepersistence.usercompoffraise.CompOffRaiseRepositoryImpl;

@RestController
@RequestMapping("/easybusiness/leave/")
public class RaiseCompOffServiceImpl implements RaiseCompOffService {

    @Autowired
    private CompOffRaiseRepositoryImpl compOffRaiseRepositoryImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(RaiseCompOffServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "raisecompoff/{userId}/{leaveStartDate}/{leaveRaiseDate}/{locationId}/{unitId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LeaveActivityResponse> raiseCompOff(@PathVariable("userId") String userId,
	    @PathVariable("leaveRaiseDate") String leaveRaiseDate, @PathVariable("locationId") String locationId,
	    @PathVariable("unitId") String unitId) {

	String responseMsg;
	HttpStatus httpStatus;
	try {
	    compOffRaiseRepositoryImpl.compOffRaise(userId,
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveRaiseDate)).getTime()), locationId,
		    unitId);
	    responseMsg = "CompOff Raised Successfully";
	    httpStatus = HttpStatus.OK;
	} catch (Exception e) {
	    LOGGER.error("exception while Raising CompOff for user by Id {} , leave apply details {},  {}", userId,
		    userId + "~" + leaveRaiseDate + "~" + locationId + "~" + unitId, e.getMessage());
	    responseMsg = "CompOff could not be raised";
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	LeaveActivityResponse leaveActivityResponse = new LeaveActivityResponse();
	leaveActivityResponse.setResponseCode(httpStatus.toString());
	leaveActivityResponse.setResponseMessage(responseMsg);
	return new ResponseEntity<LeaveActivityResponse>(leaveActivityResponse, httpStatus);
    }

}
