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
import com.gdu.myapp.dto.PracDto;
import com.gdu.myapp.dto.RanksDto;
import com.gdu.myapp.dto.RequestsDto;
import com.gdu.myapp.mapper.ApprovalMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

  private final ApprovalMapper approvalMapper;


 
  @Override
  public int insertApproval( HttpServletRequest request) {
   
    String departName = request.getParameter("departName");
    String name = request.getParameter("name");
    String rankTitle = request.getParameter("rankTitle");
    String reason = request.getParameter("reason");
    int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));
    int picNo = Integer.parseInt(request.getParameter("picNo"));
    int requestStatus = Integer.parseInt(request.getParameter("requestStatus"));
    
    DepartmentsDto depart = new DepartmentsDto();
    depart.setName(departName);
    RanksDto rank = new RanksDto();
    rank.setTitle(rankTitle);
    
    EmployeesDto employees = EmployeesDto.builder()
                                         .depart(depart)
                                         .name(name)
                                         .employeeNo(employeeNo)
                                         .rank(rank)
                                         
                                         .build();
    RequestsDto requests = RequestsDto.builder()
                                      .employees(employees)
                                      .reason(reason)
                                      .picNo(picNo)
                                      .requestStatus(requestStatus)
                                      .build();
    
    return approvalMapper.insertApproval(requests); 
  }
  
  
  @Override
  public int insertLeaveApproval(HttpServletRequest request) {
    
    String departName = request.getParameter("departName");
    String name = request.getParameter("name");
    String rankTitle = request.getParameter("rankTitle");
    String reason = request.getParameter("reason");
    int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));
    int picNo = Integer.parseInt(request.getParameter("picNo"));
    int requestStatus = Integer.parseInt(request.getParameter("requestStatus"));
    int duration = Integer.parseInt(request.getParameter("duration"));
    
    String startDateString = request.getParameter("startDate");
    String endDateString = request.getParameter("endDate");

    LocalDate startDate = LocalDate.parse(startDateString);
    LocalDate endDate = LocalDate.parse(endDateString);

    // LocalDate를 SQL Date로 변환
    Date sqlStartDate = Date.valueOf(startDate);
    Date sqlEndDate = Date.valueOf(endDate);

    // 이제 sqlStartDate와 sqlEndDate를 데이터베이스에 저장할 수 있습니다.
    
    DepartmentsDto depart = new DepartmentsDto();
    depart.setName(departName);
    RanksDto rank = new RanksDto();
    rank.setTitle(rankTitle);
    
    EmployeesDto employees = EmployeesDto.builder()
                                         .depart(depart)
                                         .name(name)
                                         .employeeNo(employeeNo)
                                         .rank(rank)
                                         
                                         .build();
    RequestsDto requests = RequestsDto.builder()
                                      .employees(employees)
                                      .reason(reason)
                                      .picNo(picNo)
                                      .requestStatus(requestStatus)
                                      .build();
    
    LeaveRequestDto leaveRequest = LeaveRequestDto.builder()
                                                  .requests(requests)
                                                  .startDate(sqlStartDate)
                                                  .endDate(sqlEndDate)
                                                  .duration(duration)
                                                  .build();
    
    return approvalMapper.insertLeaveApproval(leaveRequest);
  }

  @Override
  public int insertPrac(HttpServletRequest request) {
    int deptNo = Integer.parseInt(request.getParameter("deptNo"));
    String deptName = request.getParameter("deptName");
    
    PracDto parc = PracDto.builder()
                          .deptNo(deptNo)
                          .deptName(deptName)
                          .build();
    return approvalMapper.insertPrac(parc);
  }
  
}
