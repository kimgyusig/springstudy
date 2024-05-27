package com.gdu.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gdu.myapp.dto.LeaveRequestDto;
import com.gdu.myapp.dto.RequestsDto;

public interface ApprovalService {

  int insertApproval(HttpServletRequest request);
  int insertLeaveApproval(HttpServletRequest request);
}
