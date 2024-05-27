package com.gdu.myapp.mapper;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.myapp.dto.LeaveRequestDto;
import com.gdu.myapp.dto.PracDto;
import com.gdu.myapp.dto.RequestsDto;

@Mapper
public interface ApprovalMapper {

  int insertApproval(RequestsDto leaveRequest);
  int insertLeaveApproval(LeaveRequestDto leaveRequest);
  int insertPrac(PracDto prac);
}
