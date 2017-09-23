package com.easybusiness.leavemanagement.services.leaveallocationfornewuser;

import org.springframework.http.ResponseEntity;

public interface AllocateLeaveService {

    public ResponseEntity<String> allocateLeave(String userId);

}
