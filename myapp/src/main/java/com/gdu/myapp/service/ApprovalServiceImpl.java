package com.gdu.myapp.service;

import org.springframework.stereotype.Service;

import com.gdu.myapp.dto.RequestsDto;
import com.gdu.myapp.mapper.ApprovalMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

  private final ApprovalMapper approvalMapper;
  
  @Override
  public int insertApproval(RequestsDto requests1, RequestsDto requests2) {
    int result = approvalMapper.insertApproval(requests1);
    int result2= approvalMapper.insertLeaveApproval(requests2);
    
    return 0;
  }
  
  

}
