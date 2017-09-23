package com.easybusiness.leavemanagement.services.leavetransaction;

import static com.easybusiness.leavemanagement.constant.LeaveManagementConstant.USER_HOST_SERVER;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.easybusiness.leavemanagement.dto.DepartmentDTO;
import com.easybusiness.leavemanagement.dto.DesignationDTO;
import com.easybusiness.leavemanagement.dto.LeaveMasterDTO;
import com.easybusiness.leavemanagement.dto.LeaveTransactionDTO;
import com.easybusiness.leavemanagement.dto.LocationMasterDTO;
import com.easybusiness.leavemanagement.dto.OrganizationDTO;
import com.easybusiness.leavemanagement.dto.StatusDTO;
import com.easybusiness.leavemanagement.dto.UserDTO;
import com.easybusiness.leavepersistence.entity.Department;
import com.easybusiness.leavepersistence.entity.Designation;
import com.easybusiness.leavepersistence.entity.LeaveMaster;
import com.easybusiness.leavepersistence.entity.LeaveTransaction;
import com.easybusiness.leavepersistence.entity.LocationMaster;
import com.easybusiness.leavepersistence.entity.Organization;
import com.easybusiness.leavepersistence.entity.Status;
import com.easybusiness.leavepersistence.entity.User;
import com.easybusiness.leavepersistence.leavetransaction.LeaveTransactionDao;

@RestController
@RequestMapping("/easybusiness/leave/leavetransaction/")
public class LeaveTransactionServiceImpl implements LeaveTransactionService {

    @Autowired
    private LeaveTransactionDao leaveTransactionDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveTransactionServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveTransactionById/{leaveTransactionId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LeaveTransactionDTO> findByLeaveTransactionId(
	    @PathVariable("leaveTransactionId") String leaveTransactionId) {
	LeaveTransaction leaveTransaction = leaveTransactionDao
		.findByLeaveTransactionId(Long.parseLong(leaveTransactionId));
	LeaveTransactionDTO leaveTransactionDTO = prepareLeaveTransactionDTO(leaveTransaction);
	return new ResponseEntity<LeaveTransactionDTO>(leaveTransactionDTO, HttpStatus.OK);
    }

    private LeaveTransactionDTO prepareLeaveTransactionDTO(LeaveTransaction leaveTransaction) {

	LeaveTransactionDTO leaveTransactionDTO = new LeaveTransactionDTO();
	leaveTransactionDTO.setAppliedBy(
		null != leaveTransaction.getAppliedBy() ? prepareUserDTO(leaveTransaction.getAppliedBy()) : null);
	leaveTransactionDTO.setAppliedOn(leaveTransaction.getAppliedOn());
	leaveTransactionDTO.setApprovedBy(
		null != leaveTransaction.getApprovedBy() ? prepareUserDTO(leaveTransaction.getApprovedBy()) : null);
	leaveTransactionDTO.setApprovedOn(leaveTransaction.getApprovedOn());
	leaveTransactionDTO.setAvailedOn(leaveTransaction.getAvailedOn());
	leaveTransactionDTO.setCanceledOn(leaveTransaction.getCanceledOn());
	leaveTransactionDTO.setCreatedBy(leaveTransaction.getCreatedBy());
	leaveTransactionDTO.setCreatedOn(leaveTransaction.getCreatedOn());
	leaveTransactionDTO.setId(leaveTransaction.getId());
	leaveTransactionDTO.setLeaveEndDate(leaveTransaction.getLeaveEndDate());
	leaveTransactionDTO.setLeaveMaster(prepareLeaveMasterDTO(leaveTransaction.getLeaveMaster()));
	leaveTransactionDTO.setLeaveNo(leaveTransaction.getLeaveNo());
	leaveTransactionDTO.setLeaveStartDate(leaveTransaction.getLeaveStartDate());
	leaveTransactionDTO.setModifiedBy(leaveTransaction.getModifiedBy());
	leaveTransactionDTO.setModifiedOn(leaveTransaction.getModifiedOn());
	leaveTransactionDTO.setRaisedFor(leaveTransaction.getRaisedFor());
	leaveTransactionDTO.setRaisedOn(leaveTransaction.getRaisedOn());
	System.out.println("status inside leave transaction is "+leaveTransaction.getStatus().toString());
	leaveTransactionDTO.setStatus(
		null != leaveTransaction.getStatus() ? prepareStatusDTO(leaveTransaction.getStatus()) : null);
	leaveTransactionDTO
		.setUser(null != leaveTransaction.getUser() ? prepareUserDTO(leaveTransaction.getUser()) : null);
	return leaveTransactionDTO;
    }

    private StatusDTO prepareStatusDTO(Status status) {
	StatusDTO statusDTO = new StatusDTO();
	statusDTO.setStatusId(status.getStatusId());
	statusDTO.setStatusName(status.getStatusName());
	return statusDTO;
    }

    private UserDTO prepareUserDTO(User userEntity) {
	UserDTO userDTO = new UserDTO();
	userDTO.setAlternateEmail(userEntity.getAlternateEmail());
	userDTO.setDateOfBirth(userEntity.getDateOfBirth());
	DepartmentDTO deptDO = new DepartmentDTO();
	try {
	    deptDO.setDeptName(userEntity.getDepartment().getDeptName());
	    deptDO.setId(userEntity.getDepartment().getId());
	    OrganizationDTO orgDTO = new OrganizationDTO();
	    orgDTO.setId(userEntity.getDepartment().getOrganization().getId());
	    orgDTO.setOrgLocation(userEntity.getDepartment().getOrganization().getOrgLocation());
	    orgDTO.setOrgName(userEntity.getDepartment().getOrganization().getOrgName());
	    deptDO.setOrganization(orgDTO);
	    userDTO.setDepartment(deptDO);
	    userDTO.setOrganization(orgDTO);
	} catch (Exception e) {
	    LOGGER.error("error in getting organization/department of user {} {}", userEntity.getUserName(),
		    e.getMessage());
	}
	try {
	    DesignationDTO desigDTO = new DesignationDTO();

	    desigDTO.setDesig(userEntity.getDesignation().getDesig());
	    desigDTO.setId(userEntity.getDesignation().getId());

	    userDTO.setDesignation(desigDTO);
	} catch (Exception e) {
	    LOGGER.error("error in getting designation of user {} {}", userEntity.getUserName(), e.getMessage());
	}
	userDTO.setEmail(userEntity.getEmail());
	userDTO.setEndDate(userEntity.getEndDate());
	userDTO.setFirstName(userEntity.getFirstName());
	userDTO.setFromDate(userEntity.getFromDate());
	userDTO.setGender(userEntity.getGender());
	userDTO.setId(userEntity.getId());
	userDTO.setIsEnabled(userEntity.getIsEnabled());
	userDTO.setLastName(userEntity.getLastName());
	userDTO.setMobile(userEntity.getMobile());
	userDTO.setModifiedBy(userEntity.getModifiedBy());
	userDTO.setModifiedOn(userEntity.getModifiedOn());

	userDTO.setPassword(userEntity.getPassword());
	userDTO.setTypeOfEmployment(userEntity.getTypeOfEmployment());
	userDTO.setUserImg(null);

	userDTO.setUserName(userEntity.getUserName());
	userDTO.setPermAddr(userEntity.getPermAddr());
	userDTO.setState(userEntity.getState());
	userDTO.setCity(userEntity.getCity());
	userDTO.setCountry(userEntity.getCountry());
	userDTO.setZip(userEntity.getZip());
	userDTO.setFatherName(userEntity.getFatherName());
	userDTO.setSpouseName(userEntity.getSpouseName());
	userDTO.setPassport(userEntity.getPassport());
	userDTO.setLocation(null != userEntity.getLocation() ? prepareLocationDTO(userEntity.getLocation()) : null);
	userDTO.setUnitId(userEntity.getUnitId());

	return userDTO;
    }

    private LocationMasterDTO prepareLocationDTO(LocationMaster location) {
	LocationMasterDTO locationMaster = new LocationMasterDTO();
	locationMaster.setCreatedBy(location.getCreatedBy());
	locationMaster.setCreatedOn(location.getCreatedOn());
	locationMaster.setId(location.getId());
	locationMaster.setLocationArea(location.getLocationArea());
	locationMaster.setLocationCity(location.getLocationCity());
	locationMaster.setLocationCountry(location.getLocationCountry());
	locationMaster.setLocationPin(location.getLocationPin());
	locationMaster.setLocationState(location.getLocationState());
	locationMaster.setModifiedBy(location.getModifiedBy());
	locationMaster.setModifiedOn(location.getModifiedOn());
	return locationMaster;
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
    @RequestMapping(value = "getLeaveTransactionForUserIdAndLeaveType/{userId}/{leaveTypeId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserIdAndLeaveTypeId(
	    @PathVariable("userId") String userId, @PathVariable("leaveTypeId") String leaveTypeId) {

	List<LeaveTransaction> leaveTransactionEntityList = leaveTransactionDao
		.findLeaveTransactionByUserIdAndLeaveTypeId(Long.parseLong(userId), Long.parseLong(leaveTypeId));
	List<LeaveTransactionDTO> leaveTransactionDTOList = leaveTransactionEntityList.stream()
		.map(leaveTransactionEntityItem -> prepareLeaveTransactionDTO(leaveTransactionEntityItem))
		.collect(Collectors.toList());
	return new ResponseEntity<List<LeaveTransactionDTO>>(leaveTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveTransactionForUserId/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserId(
	    @PathVariable("userId") String userId) {
	List<LeaveTransaction> leaveTransactionEntityList = leaveTransactionDao
		.findLeaveTransactionByUserId(Long.parseLong(userId));
	List<LeaveTransactionDTO> leaveTransactionDTOList = leaveTransactionEntityList.stream()
		.map(leaveTransactionEntityItem -> prepareLeaveTransactionDTO(leaveTransactionEntityItem))
		.collect(Collectors.toList());
	return new ResponseEntity<List<LeaveTransactionDTO>>(leaveTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveTransactionForUsername/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserName(
	    @PathVariable("userName") String userName) {
	List<LeaveTransaction> leaveTransactionEntityList = leaveTransactionDao
		.findLeaveTransactionByUserName(userName);
	List<LeaveTransactionDTO> leaveTransactionDTOList = leaveTransactionEntityList.stream()
		.map(leaveTransactionEntityItem -> prepareLeaveTransactionDTO(leaveTransactionEntityItem))
		.collect(Collectors.toList());
	return new ResponseEntity<List<LeaveTransactionDTO>>(leaveTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveTransactionForUsernameAndLeaveType/{userName}/{leaveTypeId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserNameAndLeaveTypeId(
	    @PathVariable("userName") String userName, @PathVariable("leaveTypeId") String leaveTypeId) {
	List<LeaveTransaction> leaveTransactionEntityList = leaveTransactionDao
		.findLeaveTransactionByUserNameAndLeaveTypeId(userName, Long.parseLong(leaveTypeId));
	List<LeaveTransactionDTO> leaveTransactionDTOList = leaveTransactionEntityList.stream()
		.map(leaveTransactionEntityItem -> prepareLeaveTransactionDTO(leaveTransactionEntityItem))
		.collect(Collectors.toList());
	return new ResponseEntity<List<LeaveTransactionDTO>>(leaveTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveTransactionForUserIdAndLeaveTypeAndleaveDateRange/{userId}/{leaveTypeId}/{leaveStartDate}/{leaveEndDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserIdAndLeaveTypeIdAndLeaveDate(
	    @PathVariable("userId") String userId, @PathVariable("leaveTypeId") String leaveTypeId,
	    @PathVariable("leaveStartDate") String leaveStartDate, @PathVariable("leaveEndDate") String leaveEndDate) {

	List<LeaveTransaction> leaveTransactionEntityList = new ArrayList<LeaveTransaction>();
	try {
	    leaveTransactionEntityList = leaveTransactionDao.findLeaveTransactionByUserIdAndLeaveTypeIdAndLeaveDate(
		    Long.parseLong(userId), Long.parseLong(leaveTypeId),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveStartDate)).getTime()),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveEndDate)).getTime()));
	} catch (NumberFormatException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	List<LeaveTransactionDTO> leaveTransactionDTOList = leaveTransactionEntityList.stream()
		.map(leaveTransactionEntityItem -> null != leaveTransactionEntityItem
			? prepareLeaveTransactionDTO(leaveTransactionEntityItem) : new LeaveTransactionDTO())
		.collect(Collectors.toList());
	return new ResponseEntity<List<LeaveTransactionDTO>>(leaveTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAllLeaveTransactions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveTransactionDTO>> findAll() {
	List<LeaveTransaction> leaveTransactionEntityList = leaveTransactionDao.findAll();
	List<LeaveTransactionDTO> leaveTransactionDTOList = leaveTransactionEntityList.stream()
		.map(leaveTransactionEntityItem -> prepareLeaveTransactionDTO(leaveTransactionEntityItem))
		.collect(Collectors.toList());
	return new ResponseEntity<List<LeaveTransactionDTO>>(leaveTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "addOrModifyLeaveTransaction", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LeaveTransactionDTO> addOrModifyLeaveTransaction(
	    @RequestBody LeaveTransactionDTO leaveTransactionDTO) {

	HttpStatus httpStatus;
	try {
	    leaveTransactionDao.addOrModifyLeaveTransaction(prepareLeaveTransctionEntity(leaveTransactionDTO));
	    httpStatus = HttpStatus.CREATED;
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("exception in adding/modifying leave transaction  {} ,is {} ", leaveTransactionDTO.toString(),
		    e.getMessage());
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	return new ResponseEntity<LeaveTransactionDTO>(leaveTransactionDTO, httpStatus);
    }

    private LeaveTransaction prepareLeaveTransctionEntity(LeaveTransactionDTO leaveTransactionDTO) {
	LeaveTransaction leaveTransactionEntity = new LeaveTransaction();
	leaveTransactionEntity.setAppliedBy(null != leaveTransactionDTO.getAppliedBy()
		? prepareUserEntity(leaveTransactionDTO.getAppliedBy()) : null);
	leaveTransactionEntity.setAppliedOn(leaveTransactionDTO.getAppliedOn());
	leaveTransactionEntity.setApprovedBy(null != leaveTransactionDTO.getApprovedBy()
		? prepareUserEntity(leaveTransactionDTO.getApprovedBy()) : null);
	leaveTransactionEntity.setApprovedOn(leaveTransactionDTO.getApprovedOn());
	leaveTransactionEntity.setAvailedOn(leaveTransactionDTO.getAvailedOn());
	leaveTransactionEntity.setCanceledOn(leaveTransactionDTO.getCanceledOn());
	leaveTransactionEntity.setCreatedBy(leaveTransactionDTO.getCreatedBy());
	leaveTransactionEntity.setCreatedOn(leaveTransactionDTO.getCreatedOn());
	leaveTransactionEntity.setId(leaveTransactionDTO.getId());
	leaveTransactionEntity.setLeaveEndDate(leaveTransactionDTO.getLeaveEndDate());
	leaveTransactionEntity.setLeaveMaster(prepareLeaveMasterEntity(leaveTransactionDTO.getLeaveMaster()));
	leaveTransactionEntity.setLeaveNo(leaveTransactionDTO.getLeaveNo());
	leaveTransactionEntity.setLeaveStartDate(leaveTransactionDTO.getLeaveStartDate());
	leaveTransactionEntity.setModifiedBy(leaveTransactionDTO.getModifiedBy());
	leaveTransactionEntity.setModifiedOn(leaveTransactionDTO.getModifiedOn());
	leaveTransactionEntity.setRaisedFor(leaveTransactionDTO.getRaisedFor());
	leaveTransactionEntity.setRaisedOn(leaveTransactionDTO.getRaisedOn());
	leaveTransactionEntity.setStatus(
		null != leaveTransactionDTO.getStatus() ? prepareStatusEntity(leaveTransactionDTO.getStatus()) : null);
	leaveTransactionEntity.setUser(prepareUserEntity(leaveTransactionDTO.getUser()));
	return leaveTransactionEntity;
    }

    private Status prepareStatusEntity(StatusDTO status) {
	Status statusEntity = new Status();
	statusEntity.setStatusId(status.getStatusId());
	statusEntity.setStatusName(status.getStatusName());
	return statusEntity;
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

    private User prepareUserEntity(UserDTO userDTO) {
	User userEntity = new User();
	userEntity.setAlternateEmail(userDTO.getAlternateEmail());
	userEntity.setDateOfBirth(userDTO.getDateOfBirth());
	Department dept = new Department();
	dept.setDeptName(userDTO.getDepartment().getDeptName());
	dept.setId(userDTO.getDepartment().getId());
	Organization org = new Organization();
	org.setId(userDTO.getDepartment().getOrganization().getId());
	org.setOrgLocation(userDTO.getDepartment().getOrganization().getOrgLocation());
	org.setOrgName(userDTO.getDepartment().getOrganization().getOrgName());
	dept.setOrganization(org);
	userEntity.setDepartment(dept);
	Designation desg = new Designation();
	desg.setDesig(userDTO.getDesignation().getDesig());
	desg.setId(userDTO.getDesignation().getId());
	userEntity.setDesignation(desg);
	userEntity.setEmail(userDTO.getEmail());
	userEntity.setEndDate(userDTO.getEndDate());
	userEntity.setFirstName(userDTO.getFirstName());
	userEntity.setFromDate(userDTO.getFromDate());
	userEntity.setGender(userDTO.getGender());
	userEntity.setIsEnabled(userDTO.getIsEnabled());
	userEntity.setLastName(userDTO.getLastName());
	userEntity.setMobile(userDTO.getMobile());
	userEntity.setModifiedBy(userDTO.getModifiedBy());
	userEntity.setModifiedOn(userDTO.getModifiedOn());
	userEntity.setOrganization(org);
	userEntity.setPassword(userDTO.getPassword());
	userEntity.setTypeOfEmployment(userDTO.getTypeOfEmployment());
	userEntity.setUserName(userDTO.getUserName());
	userEntity.setId(userDTO.getId());

	userEntity.setPermAddr(userDTO.getPermAddr());
	userEntity.setState(userDTO.getState());
	userEntity.setCity(userDTO.getCity());
	userEntity.setCountry(userDTO.getCountry());
	userEntity.setZip(userDTO.getZip());
	userEntity.setFatherName(userDTO.getFatherName());
	userEntity.setSpouseName(userDTO.getSpouseName());
	userEntity.setPassport(userDTO.getPassport());
	userEntity.setLocation(null == userDTO.getLocation() ? null : prepareLocationEntity(userDTO.getLocation()));
	userEntity.setUnitId(userDTO.getUnitId());

	return userEntity;
    }

    private LocationMaster prepareLocationEntity(LocationMasterDTO location) {
	LocationMaster locationMaster = new LocationMaster();
	locationMaster.setCreatedBy(location.getCreatedBy());
	locationMaster.setCreatedOn(location.getCreatedOn());
	locationMaster.setId(location.getId());
	locationMaster.setLocationArea(location.getLocationArea());
	locationMaster.setLocationCity(location.getLocationCity());
	locationMaster.setLocationCountry(location.getLocationCountry());
	locationMaster.setLocationPin(location.getLocationPin());
	locationMaster.setLocationState(location.getLocationState());
	locationMaster.setModifiedBy(location.getModifiedBy());
	locationMaster.setModifiedOn(location.getModifiedOn());
	return locationMaster;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "deleteLeaveTransaction/{leaveTransactionId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteLeaveTransaction(String leaveTransactionId) {
	try {
	    leaveTransactionDao.deleteLeaveTransaction(Long.parseLong(leaveTransactionId));
	} catch (Exception e) {
	    LOGGER.error("exception in dleting leave transaction id {} ,is {} ", leaveTransactionId, e.getMessage());
	}

    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getCountOfLeavesForUserIdAndLeaveTypeAndleaveDateRange/{userId}/{leaveTypeId}/{leaveStartDate}/{leaveEndDate}", method = RequestMethod.GET)
    @ResponseBody
    public int countOfLeaveTransactions(@PathVariable("userId") String userId,
	    @PathVariable("leaveTypeId") String leaveTypeId, @PathVariable("leaveStartDate") String leaveStartDate,
	    @PathVariable("leaveEndDate") String leaveEndDate) {

	try {
	    return leaveTransactionDao.countOfLeavesApplied(Long.parseLong(userId), Long.parseLong(leaveTypeId),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveStartDate)).getTime()),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveEndDate)).getTime()));
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return 0;
	}

    }

    // @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveTransactionForUserIdAndLeaveTypeAndDateRange/{userId}/{leaveTypeId}/{leaveStartDate}/{leaveEndDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveTransactionDTO>> findLeaveTransactionByUserIdAndLeaveTypeIdAndDateRange(
	    @PathVariable("userId") String userId, @PathVariable("leaveTypeId") String leaveTypeId,
	    @PathVariable("leaveStartDate") String leaveStartDate, @PathVariable("leaveEndDate") String leaveEndDate) {

	List<LeaveTransaction> leaveTransactionEntityList = new ArrayList<LeaveTransaction>();
	try {
	    leaveTransactionEntityList = leaveTransactionDao.findLeaveTransactionByUserIdAndLeaveTypeIdAndLeaveDate(
		    Long.parseLong(userId), Long.parseLong(leaveTypeId),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveStartDate)).getTime()),
		    new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(leaveEndDate)).getTime()));
	} catch (NumberFormatException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	List<LeaveTransactionDTO> leaveTransactionDTOList = leaveTransactionEntityList.stream()
		.map(leaveTransactionEntityItem -> null != leaveTransactionEntityItem
			? prepareLeaveTransactionDTO(leaveTransactionEntityItem) : new LeaveTransactionDTO())
		.collect(Collectors.toList());
	return new ResponseEntity<List<LeaveTransactionDTO>>(leaveTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveTransactionAsPerStatus/{status}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveTransactionDTO>> findByLeaveStatus(@PathVariable("status") String leaveStatus) {
	List<LeaveTransaction> leaveTransactionEntityList = new ArrayList<LeaveTransaction>();
	try {
	    leaveTransactionEntityList = leaveTransactionDao.findLeaveTransactionByStatus(leaveStatus);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	List<LeaveTransactionDTO> leaveTransactionDTOList = leaveTransactionEntityList.stream()
		.map(leaveTransactionEntityItem -> null != leaveTransactionEntityItem
			? prepareLeaveTransactionDTO(leaveTransactionEntityItem) : new LeaveTransactionDTO())
		.collect(Collectors.toList());
	return new ResponseEntity<List<LeaveTransactionDTO>>(leaveTransactionDTOList, HttpStatus.OK);
    }

}
