package com.easybusiness.leavemanagement.services.leavebalance;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybusiness.leavemanagement.dto.DepartmentDTO;
import com.easybusiness.leavemanagement.dto.DesignationDTO;
import com.easybusiness.leavemanagement.dto.LeaveBalanceDTO;
import com.easybusiness.leavemanagement.dto.LeaveMasterDTO;
import com.easybusiness.leavemanagement.dto.LocationMasterDTO;
import com.easybusiness.leavemanagement.dto.OrganizationDTO;
import com.easybusiness.leavemanagement.dto.UserDTO;
import com.easybusiness.leavepersistence.entity.Department;
import com.easybusiness.leavepersistence.entity.Designation;
import com.easybusiness.leavepersistence.entity.LeaveBalance;
import com.easybusiness.leavepersistence.entity.LeaveMaster;
import com.easybusiness.leavepersistence.entity.LocationMaster;
import com.easybusiness.leavepersistence.entity.Organization;
import com.easybusiness.leavepersistence.entity.User;
import com.easybusiness.leavepersistence.leavebalance.LeaveBalanceDao;

@RestController
@RequestMapping("/easybusiness/leave/leavebalance")
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

    @Autowired
    private LeaveBalanceDao leaveBalanceDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveBalanceServiceImpl.class);

    /*
     * @Override
     * 
     * @CrossOrigin(origins = USER_HOST_SERVER)
     * 
     * @RequestMapping(value = "getLeaveTransactionById/{leaveTransactionId}",
     * method = RequestMethod.GET)
     * 
     * @ResponseBody public ResponseEntity<LeaveTransactionDTO>
     * findByLeaveTransactionId(
     * 
     * @PathVariable("leaveTransactionId") String leaveTransactionId) {
     * LeaveTransaction leaveTransaction = leaveTransactionDao
     * .findByLeaveTransactionId(Long.parseLong(leaveTransactionId));
     * LeaveTransactionDTO leaveTransactionDTO =
     * prepareLeaveTransactionDTO(leaveTransaction); return new
     * ResponseEntity<LeaveTransactionDTO>(leaveTransactionDTO, HttpStatus.OK);
     * }
     */

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
    @RequestMapping(value = "getLeaveBalanceById/{leaveBalanceId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LeaveBalanceDTO> findByLeaveBalanceId(@PathVariable("leaveBalanceId") String leaveBalanceId) {

	LeaveBalance leaveBalance = leaveBalanceDao.findByLeaveBalanceId(Long.parseLong(leaveBalanceId));
	LeaveBalanceDTO leaveBalanceDTO = null!=leaveBalance?prepareLeaveBalanceDTO(leaveBalance):new LeaveBalanceDTO();
	return new ResponseEntity<LeaveBalanceDTO>(leaveBalanceDTO, HttpStatus.OK);
    }

    private LeaveBalanceDTO prepareLeaveBalanceDTO(LeaveBalance leaveBalance) {
	LeaveBalanceDTO leaveBalanceDTO = new LeaveBalanceDTO();
	leaveBalanceDTO.setCreatedBy(leaveBalance.getCreatedBy());
	leaveBalanceDTO.setCreatedOn(leaveBalance.getCreatedOn());
	leaveBalanceDTO.setId(leaveBalance.getId());
	leaveBalanceDTO.setLeaveApplied(leaveBalance.getLeaveApplied());
	leaveBalanceDTO.setLeaveBalance(leaveBalance.getLeaveBalance());
	leaveBalanceDTO.setLeaveEarned(leaveBalance.getLeaveEarned());
	leaveBalanceDTO.setLeaveLapsed(leaveBalance.getLeaveLapsed());
	leaveBalanceDTO.setLeaveMaster(prepareLeaveMasterDTO(leaveBalance.getLeaveMaster()));
	leaveBalanceDTO.setLocationMaster(null!=leaveBalance.getLocationMaster()?prepareLocationDTO(leaveBalance.getLocationMaster()):null);
	leaveBalanceDTO.setModifiedBy(leaveBalance.getModifiedBy());
	leaveBalanceDTO.setModifiedOn(leaveBalance.getModifiedOn());
	leaveBalanceDTO.setOpeningBalance(leaveBalance.getOpeningBalance());
	leaveBalanceDTO.setTotalLeave(leaveBalance.getTotalLeave());
	leaveBalanceDTO.setUser(prepareUserDTO(leaveBalance.getUser()));
	leaveBalanceDTO.setValidFrom(leaveBalance.getValidFrom());
	leaveBalanceDTO.setValidTo(leaveBalance.getValidTo());
	return leaveBalanceDTO;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveBalanceByUserIdAndLeaveType/{userId}/{leaveTypeId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveBalanceDTO>> findLeaveBalanceByUserIdAndLeaveTypeId(
	    @PathVariable("userId") String userId, @PathVariable("leaveTypeId") String leaveTypeId) {

	List<LeaveBalance> leaveBalanceList = leaveBalanceDao
		.findLeaveBalanceByUserIdAndLeaveTypeId(Long.parseLong(userId), Long.parseLong(leaveTypeId));
	List<LeaveBalanceDTO> leaveBalanceDTOList = leaveBalanceList.stream()
		.map(leaveBalanceListItem -> prepareLeaveBalanceDTO(leaveBalanceListItem)).collect(Collectors.toList());

	return new ResponseEntity<List<LeaveBalanceDTO>>(leaveBalanceDTOList, HttpStatus.OK);

    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveBalanceByUserId/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveBalanceDTO>> findLeaveBalanceByUserId(@PathVariable("userId") String userId) {

	List<LeaveBalance> leaveBalanceList = leaveBalanceDao.findLeaveBalanceByUserId(Long.parseLong(userId));
	List<LeaveBalanceDTO> leaveBalanceDTOList = leaveBalanceList.stream()
		.map(leaveBalanceListItem -> prepareLeaveBalanceDTO(leaveBalanceListItem)).collect(Collectors.toList());

	return new ResponseEntity<List<LeaveBalanceDTO>>(leaveBalanceDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveBalanceByUserName/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveBalanceDTO>> findLeaveBalanceByUserName(@PathVariable("userName") String userName) {

	List<LeaveBalance> leaveBalanceList = leaveBalanceDao.findLeaveBalanceByUserName(userName);
	List<LeaveBalanceDTO> leaveBalanceDTOList = leaveBalanceList.stream()
		.map(leaveBalanceListItem -> prepareLeaveBalanceDTO(leaveBalanceListItem)).collect(Collectors.toList());

	return new ResponseEntity<List<LeaveBalanceDTO>>(leaveBalanceDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getLeaveBalanceByUserNameAndLeaveType/{userName}/{leaveTypeId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LeaveBalanceDTO>> findLeaveBalanceByUserNameAndLeaveTypeId(
	    @PathVariable("userName") String userName, @PathVariable("leaveTypeId") String leaveTypeId) {

	List<LeaveBalance> leaveBalanceList = leaveBalanceDao.findLeaveBalanceByUserNameAndLeaveTypeId(userName,
		Long.parseLong(leaveTypeId));
	List<LeaveBalanceDTO> leaveBalanceDTOList = leaveBalanceList.stream()
		.map(leaveBalanceListItem -> prepareLeaveBalanceDTO(leaveBalanceListItem)).collect(Collectors.toList());

	return new ResponseEntity<List<LeaveBalanceDTO>>(leaveBalanceDTOList, HttpStatus.OK);
    }

}
