package com.gdu.myapp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
public int insertLeaveApproval(HttpServletRequest request) {
  int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));
  String departName = request.getParameter("departName");
  String name = request.getParameter("name");
  String rankTitle = request.getParameter("rankTitle");
  int leaveType = Integer.parseInt(request.getParameter("leaveType"));
  String reason = request.getParameter("reason");
  String startDateString = request.getParameter("startDate");
  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  LocalDate localDate = LocalDate.parse(startDateString, dateFormatter);
  java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
  String endDateString = request.getParameter("startDate");
  DateTimeFormatter dateFormatterend = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  LocalDate localDate2 = LocalDate.parse(startDateString, dateFormatter);
  java.sql.Date endDate = java.sql.Date.valueOf(localDate);

  
  DepartmentsDto depart = DepartmentsDto.builder()
      .name(departName)
      .build();

  EmployeesDto employees = EmployeesDto.builder()
    .employeeNo(employeeNo)
     .depart(depart)
     .name(name)
     .build();
RequestsDto requests = RequestsDto.builder()
  .reason(reason)
  .employees(employees)
  .build();
LeaveRequestDto leaveRequest2 = LeaveRequestDto.builder()
               .leaveType(leaveType)
               .startDate(sqlDate)
               .endDate(endDate)
               .requests(requests)
               .build();
  

return approvalMapper.insertApproval(requests); // 생성된 REQUEST_NO를 반환
  
}
 
  @Override
  public int insertApproval( HttpServletRequest request) {
   
    int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));
    String departName = request.getParameter("departName");
    String name = request.getParameter("name");
    String rankTitle = request.getParameter("rankTitle");
    int leaveType = Integer.parseInt(request.getParameter("leaveType"));
    String reason = request.getParameter("reason");
    String startDateString = request.getParameter("startDate");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(startDateString, dateFormatter);
    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
    String endDateString = request.getParameter("startDate");
    DateTimeFormatter dateFormatterend = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate2 = LocalDate.parse(startDateString, dateFormatter);
    java.sql.Date endDate = java.sql.Date.valueOf(localDate);

    
    DepartmentsDto depart = DepartmentsDto.builder()
        .name(departName)
        .build();

    EmployeesDto employees = EmployeesDto.builder()
      .employeeNo(employeeNo)
       .depart(depart)
       .name(name)
       .build();
RequestsDto requests = RequestsDto.builder()
    .reason(reason)
    .employees(employees)
    .build();

  
    return approvalMapper.insertApproval(requests);
  }
  

}
