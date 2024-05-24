package com.gdu.myapp.service;

import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gdu.myapp.dto.DepartmentsDto;
import com.gdu.myapp.dto.EmployeesDto;
import com.gdu.myapp.dto.LeaveRequestDto;
import com.gdu.myapp.dto.RequestsDto;
import com.gdu.myapp.mapper.ApprovalMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

  private final ApprovalMapper approvalMapper;
  
 @Override
public int insertApproval(Map<String, Object> map) {
 
   DepartmentsDto depart = DepartmentsDto.builder()
                                         .name((String)map.get("name"))
                                         .build();
   
   EmployeesDto employees = EmployeesDto.builder()
                                       .employeeNo((int)map.get("employeeNo"))
                                        .depart(depart)
                                        .name((String)map.get("name"))
                                        .build();
   RequestsDto requests = RequestsDto.builder()
                                     .reason((String)map.get("reason"))
                                     .employess(employees)
                                     .build();
    LeaveRequestDto leaveRequest = LeaveRequestDto.builder()
                                                  .leaveType((int)map.get("leaveType"))
                                                  .startDate((Date)map.get("startDate"))
                                                  .endDate((Date)map.get("endDate"))
                                                  .requests(requests)
                                                  .build();
   
    int insertCount = approvalMapper.insertApproval(requests);
    int insertCount2 = approvalMapper.insertLeaveApproval(requests);
    return insertCount;
}
 
  
  

}
