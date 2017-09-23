package com.easybusiness.leavemanagement.services.holidaymaster;

import static com.easybusiness.leavemanagement.constant.LeaveManagementConstant.USER_HOST_SERVER;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.easybusiness.leavemanagement.dto.HolidayMasterDTO;
import com.easybusiness.leavemanagement.dto.LocationMasterDTO;
import com.easybusiness.leavepersistence.entity.HolidayMaster;
import com.easybusiness.leavepersistence.entity.LocationMaster;
import com.easybusiness.leavepersistence.holidaymaster.HolidayDao;
import com.easybusiness.leavepersistence.locationmaster.LocationMasterDao;

@RestController
@RequestMapping("/easybusiness/leave/holidaymaster/")
public class HolidayMasterServiceImpl implements HolidayMasterService {

    @Autowired
    private HolidayDao holidayMasterDao;
    
    @Autowired
    private LocationMasterDao locationMasterDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(HolidayMasterServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getFromHolidayMasterById/{holidayMasterId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HolidayMasterDTO> findById(@PathVariable("holidayMasterId") String holidayMasterId) {

	HolidayMaster holidayMaster = holidayMasterDao.findByHolidayId(Long.parseLong(holidayMasterId));
	HolidayMasterDTO holidayMasterDTO = null!=holidayMaster?prepareHolidayMasterDTO(holidayMaster):prepareHolidayMasterDTO(new HolidayMaster());
	return new ResponseEntity<HolidayMasterDTO>(holidayMasterDTO, HttpStatus.OK);
    }

    private HolidayMasterDTO prepareHolidayMasterDTO(HolidayMaster holidayMaster) {
	HolidayMasterDTO holidayMasterDTO = new HolidayMasterDTO();
	if(null!=holidayMaster.getId())
	{
	holidayMasterDTO.setId(holidayMaster.getId());
	holidayMasterDTO.setCreatedBy(holidayMaster.getCreatedBy());
	holidayMasterDTO.setCreatedOn(holidayMaster.getCreatedOn());
	holidayMasterDTO.setDescription(holidayMaster.getDescription());
	holidayMasterDTO.setHolidayDate(holidayMaster.getHolidayDate());
	holidayMasterDTO.setHolidayType(holidayMaster.getHolidayType());
	holidayMasterDTO.setHolidayYear(holidayMaster.getHolidayYear());
	holidayMasterDTO.setLocationMaster(null!=holidayMaster.getLocationMaster()?prepareLocationDTO(holidayMaster.getLocationMaster()):null);
	holidayMasterDTO.setModifiedBy(holidayMaster.getModifiedBy());
	holidayMasterDTO.setModifiedOn(holidayMaster.getModifiedOn());
	}
	return holidayMasterDTO;
    }
    
    private LocationMasterDTO prepareLocationDTO(LocationMaster location) {
  	LocationMasterDTO locationMaster=new LocationMasterDTO();
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
    @RequestMapping(value = "getFromHolidayMasterByHolidayDate/{holidayDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HolidayMasterDTO> findByHolidayDate(@PathVariable("holidayDate") String holidayDate) {
	HolidayMaster holidayMaster=new HolidayMaster();
	try {
	    List<HolidayMaster>holidayMasterList = holidayMasterDao.findByHolidayDate(new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(holidayDate)).getTime()));
	    
	    if(holidayMasterList.size()>0)
	    {
		holidayMaster=holidayMasterList.get(0);
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	HolidayMasterDTO holidayMasterDTO = null!=holidayMaster.getId()?prepareHolidayMasterDTO(holidayMaster):new HolidayMasterDTO();
	return new ResponseEntity<HolidayMasterDTO>(holidayMasterDTO, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "findByHolidayTypeAndHolidayYear/{holidayType}/{holidayYear}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<HolidayMasterDTO>> findByHolidayTypeAndHolidayYear(@PathVariable("holidayType")String holidayType,@PathVariable("holidayYear")String holidayYear) {
	List<HolidayMaster> holidayMasterEntity = holidayMasterDao.findByHolidayTypeAndHolidayYear(holidayType, Long.parseLong(holidayYear));
	List<HolidayMasterDTO> holidayMasterDTOList = holidayMasterEntity.stream()
		.map(holidayMasterElement -> prepareHolidayMasterDTO(holidayMasterElement)).collect(Collectors.toList());
	return new ResponseEntity<List<HolidayMasterDTO>>(holidayMasterDTOList, HttpStatus.OK);
    }
    
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "findByholidayYear/{holidayYear}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<HolidayMasterDTO>> findByholidayYear(@PathVariable("holidayYear")String holidayYear)
    {
	List<HolidayMaster> holidayMasterEntity = holidayMasterDao.findByHolidayYear(Long.parseLong(holidayYear));
	List<HolidayMasterDTO> holidayMasterDTOList = holidayMasterEntity.stream()
		.map(holidayMasterElement -> prepareHolidayMasterDTO(holidayMasterElement)).collect(Collectors.toList());
	return new ResponseEntity<List<HolidayMasterDTO>>(holidayMasterDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "findByLocationIdAndHolidayYear/{locationId}/{holidayYear}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<HolidayMasterDTO>> findByLocationIdAndHolidayYear(@PathVariable("locationId")String locationId,@PathVariable("holidayYear") String holidayYear)
    {
	LocationMaster locationMaster=locationMasterDao.findById(Long.parseLong(locationId));
	List<HolidayMaster> holidayMasterEntity = holidayMasterDao.findHolidayByLocationAndHolidayYear(locationMaster, Long.parseLong(holidayYear));
	List<HolidayMasterDTO> holidayMasterDTOList = holidayMasterEntity.stream()
		.map(holidayMasterElement -> prepareHolidayMasterDTO(holidayMasterElement)).collect(Collectors.toList());
	return new ResponseEntity<List<HolidayMasterDTO>>(holidayMasterDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "addOrModifyHolidayMaster", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HolidayMasterDTO> addHolidayMaster(@RequestBody HolidayMasterDTO holidayMasterDTO) {

	HttpStatus httpStatus;
	try {
	    holidayMasterDao.addHolidayMaster(prepareHolidayMasterEntity(holidayMasterDTO));
	    httpStatus = HttpStatus.CREATED;
	} catch (Exception e) {
	    LOGGER.error("exxception while addding/ modifying holiday master is {}  {} ", holidayMasterDTO.toString(),
		    e.getMessage());
	    httpStatus = HttpStatus.EXPECTATION_FAILED;
	}
	return new ResponseEntity<HolidayMasterDTO>(holidayMasterDTO, httpStatus);
    }

    private HolidayMaster prepareHolidayMasterEntity(HolidayMasterDTO holidayMaster) {
	HolidayMaster holidayMasterEntity = new HolidayMaster();
	holidayMasterEntity.setId(holidayMaster.getId());
	holidayMasterEntity.setCreatedBy(holidayMaster.getCreatedBy());
	holidayMasterEntity.setCreatedOn(holidayMaster.getCreatedOn());
	holidayMasterEntity.setDescription(holidayMaster.getDescription());
	holidayMasterEntity.setHolidayDate(holidayMaster.getHolidayDate());
	holidayMasterEntity.setHolidayType(holidayMaster.getHolidayType());
	holidayMasterEntity.setHolidayYear(holidayMaster.getHolidayYear());
	holidayMasterEntity.setLocationMaster(null!=holidayMaster.getLocationMaster()?prepareLocationEntity(holidayMaster.getLocationMaster()):null);
	holidayMasterEntity.setModifiedBy(holidayMaster.getModifiedBy());
	holidayMasterEntity.setModifiedOn(holidayMaster.getModifiedOn());
	return holidayMasterEntity;
    }
    
    private LocationMaster prepareLocationEntity(LocationMasterDTO location) {
  	LocationMaster locationMasterEntity=new LocationMaster();
  	locationMasterEntity.setCreatedBy(location.getCreatedBy());
  	locationMasterEntity.setCreatedOn(location.getCreatedOn());
  	locationMasterEntity.setId(location.getId());
  	locationMasterEntity.setLocationArea(location.getLocationArea());
  	locationMasterEntity.setLocationCity(location.getLocationCity());
  	locationMasterEntity.setLocationCountry(location.getLocationCountry());
  	locationMasterEntity.setLocationPin(location.getLocationPin());
  	locationMasterEntity.setLocationState(location.getLocationState());
  	locationMasterEntity.setModifiedBy(location.getModifiedBy());
  	locationMasterEntity.setModifiedOn(location.getModifiedOn());
  	return locationMasterEntity;
      }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "deleteFromHolidayMaster/{leaveTypeId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteHolidayMaster(@PathVariable("leaveTypeId") String leaveTypeId) {
	holidayMasterDao.deleteHolidayMaster(Long.parseLong(leaveTypeId));

    }

}
