package com.easybusiness.leavemanagement.services.leavemaster;

import static com.easybusiness.leavemanagement.constant.LeaveManagementConstant.USER_HOST_SERVER;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybusiness.leavemanagement.dto.LeaveMasterDTO;
import com.easybusiness.leavepersistence.entity.LeaveMaster;
import com.easybusiness.leavepersistence.leavemaster.LeaveMasterDao;

@RestController
@RequestMapping("/easybusiness/leave/leavemaster/")
public class LeaveMasterServiceImpl implements LeaveMasterService {

    @Autowired
    private LeaveMasterDao leaveMasterDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveMasterServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getFromLeaveMasterById/{leaveMasterId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LeaveMasterDTO> findByLeaveTypeId(@PathVariable("leaveMasterId") String leaveMasterId) {

	LeaveMaster leaveMaster = leaveMasterDao.findByLeaveTypeId(Long.parseLong(leaveMasterId));
	LeaveMasterDTO leaveMasterDTO = prepareLeaveMasterDTO(leaveMaster);
	return new ResponseEntity<LeaveMasterDTO>(leaveMasterDTO, HttpStatus.OK);
    }

    private LeaveMasterDTO prepareLeaveMasterDTO(LeaveMaster leaveMaster) {
	LeaveMasterDTO leaveMasterDTO = new LeaveMasterDTO();
	leaveMasterDTO.setId(leaveMaster.getId());
	leaveMasterDTO.setCarryForwardLimit(leaveMaster.getCarryForwardLimit());
	leaveMasterDTO.setCreatedBy(leaveMaster.getCreatedBy());
	leaveMasterDTO.setCreatedOn(leaveMaster.getCreatedOn());
	leaveMasterDTO.setIsCarryForward(leaveMaster.getIsCarryForward());
	leaveMasterDTO.setLeaveType(leaveMaster.getLeaveType());
	leaveMasterDTO.setMaxNumInAYear(leaveMaster.getMaxNumInAYear());
	leaveMasterDTO.setModifiedBy(leaveMaster.getModifiedBy());
	leaveMasterDTO.setModifiedOn(leaveMaster.getModifiedOn());
	leaveMasterDTO.setStatus(leaveMaster.getStatus());
	leaveMasterDTO.setValidity(leaveMaster.getValidity());
	return leaveMasterDTO;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getFromLeaveMasterByDesc/{leaveTypeDesc}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LeaveMasterDTO> findByLeaveTypeDesc(@PathVariable("leaveTypeDesc") String leaveTypeDesc) {
	LeaveMaster leaveMaster = leaveMasterDao.findByLeaveType(leaveTypeDesc);
	LeaveMasterDTO leaveMasterDTO = prepareLeaveMasterDTO(leaveMaster);
	return new ResponseEntity<LeaveMasterDTO>(leaveMasterDTO, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAllFromLeaveMaster", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveMasterDTO>> findAllFromLeaveMaster() {
	List<LeaveMaster> leaveMasterEntity = leaveMasterDao.findAll();
	List<LeaveMasterDTO> leaveMasterDTOList = leaveMasterEntity.stream()
		.map(leaveMasterElement -> prepareLeaveMasterDTO(leaveMasterElement)).collect(Collectors.toList());
	return new ResponseEntity<List<LeaveMasterDTO>>(leaveMasterDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "addOrModifyLeaveMaster", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LeaveMasterDTO> addLeaveMaster(@RequestBody LeaveMasterDTO leaveMasterDTO) {

	HttpStatus httpStatus;
	try {
	    leaveMasterDao.addLeaveMaster(prepareLeaveMasterEntity(leaveMasterDTO));
	    httpStatus = HttpStatus.CREATED;
	} catch (Exception e) {
	    LOGGER.error("exxception while addding/ modifying leave master is {}  {} ", leaveMasterDTO.toString(),
		    e.getMessage());
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	return new ResponseEntity<LeaveMasterDTO>(leaveMasterDTO, httpStatus);
    }

    private LeaveMaster prepareLeaveMasterEntity(LeaveMasterDTO leaveMasterDTO) {
	LeaveMaster leaveMasterEntity = new LeaveMaster();
	leaveMasterEntity.setId(leaveMasterDTO.getId());
	leaveMasterEntity.setCarryForwardLimit(leaveMasterDTO.getCarryForwardLimit());
	leaveMasterEntity.setCreatedBy(leaveMasterDTO.getCreatedBy());
	leaveMasterEntity.setCreatedOn(leaveMasterDTO.getCreatedOn());
	leaveMasterEntity.setIsCarryForward(leaveMasterDTO.getIsCarryForward());
	leaveMasterEntity.setLeaveType(leaveMasterDTO.getLeaveType());
	leaveMasterEntity.setMaxNumInAYear(leaveMasterDTO.getMaxNumInAYear());
	leaveMasterEntity.setModifiedBy(leaveMasterDTO.getModifiedBy());
	leaveMasterEntity.setModifiedOn(leaveMasterDTO.getModifiedOn());
	leaveMasterEntity.setStatus(leaveMasterDTO.getStatus());
	leaveMasterEntity.setValidity(leaveMasterDTO.getValidity());
	return leaveMasterEntity;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "deleteFromLeaveMaster/{leaveTypeId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteLeaveMaster(@PathVariable("leaveTypeId") String leaveTypeId) {
	leaveMasterDao.deleteLeaveMaster(Long.parseLong(leaveTypeId));

    }

}
