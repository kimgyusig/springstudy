package com.gdu.academix.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.academix.dto.LeaveRequestDto;
import com.gdu.academix.dto.RequestsDto;

@Mapper
public interface RequestsMapper {

  int insertRequest(RequestsDto requests);
  int insertLeaveRequest(LeaveRequestDto leaveRequest);
  
}
