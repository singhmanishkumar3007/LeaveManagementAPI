package com.easybusiness.leavemanagement.services.leavemaster;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.leavemanagement.dto.LeaveMasterDTO;

public interface LeaveMasterService {

    public ResponseEntity<LeaveMasterDTO> findByLeaveTypeId(String leaveTypeId);

    public ResponseEntity<LeaveMasterDTO> findByLeaveTypeDesc(String leaveTypeDesc);

    public ResponseEntity<List<LeaveMasterDTO>> findAllFromLeaveMaster();

    public ResponseEntity<LeaveMasterDTO> addLeaveMaster(LeaveMasterDTO leaveMaster);

    public void deleteLeaveMaster(String leaveTypeId);

}
