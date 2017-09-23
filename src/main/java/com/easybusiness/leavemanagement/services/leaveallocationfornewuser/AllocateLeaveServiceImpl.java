package com.easybusiness.leavemanagement.services.leaveallocationfornewuser;

import static com.easybusiness.leavemanagement.constant.LeaveManagementConstant.USER_HOST_SERVER;

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

import com.easybusiness.leavepersistence.createuser.LeaveUpdateUserCreationRepositoryImpl;

@RestController
@RequestMapping("/easybusiness/leave/")
public class AllocateLeaveServiceImpl implements AllocateLeaveService {

    @Autowired
    private LeaveUpdateUserCreationRepositoryImpl leaveUpdateUserCreationRepositoryImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(AllocateLeaveServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "allocateleavefornewuser/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> allocateLeave(@PathVariable("userId") String userId) {

	String responseMsg;
	HttpStatus httpStatus;
	try {
	    leaveUpdateUserCreationRepositoryImpl.leaveUpdateForUserCreation(userId);
	    responseMsg = "Leave Successfully Allocated to New User";
	    httpStatus = HttpStatus.OK;
	} catch (Exception e) {
	    LOGGER.error("exception while allocating leave for new  user  Id {} ,   {}", userId, e.getMessage());
	    responseMsg = "Leave could not be Allocated to New User";
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	return new ResponseEntity<String>(responseMsg, httpStatus);
    }

}
