package com.gdu.academix.service;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestsService {

  int registerApproval(HttpServletRequest request);
  int insertRequest(HttpServletRequest request);
  void insertLeaveRequest(HttpServletRequest request, int requestNo);
  
}
