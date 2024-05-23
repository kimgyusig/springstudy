package com.gdu.myapp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.myapp.dto.RequestsDto;

@Mapper
public interface ApprovalMapper {

  int insertApproval(RequestsDto requests);
  int insertLeaveApproval(RequestsDto requests);
}
