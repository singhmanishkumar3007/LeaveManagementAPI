package com.easybusiness.leavemanagement.services.holidaymaster;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.HolidayMasterDTO;

public interface HolidayMasterService {

    public ResponseEntity<HolidayMasterDTO> findById(String holidayId);

    public ResponseEntity<HolidayMasterDTO> findByHolidayDate(String holidayDate);

    public ResponseEntity<List<HolidayMasterDTO>> findByHolidayTypeAndHolidayYear(String holidayType,String holidayYear);

    public ResponseEntity<List<HolidayMasterDTO>> findByholidayYear(String holidayYear);

    public ResponseEntity<List<HolidayMasterDTO>> findByLocationIdAndHolidayYear(String locationId, String holidayYear);

    public ResponseEntity<HolidayMasterDTO> addHolidayMaster(HolidayMasterDTO holidayMasterDTO);

    public void deleteHolidayMaster(String holidayId);

}
