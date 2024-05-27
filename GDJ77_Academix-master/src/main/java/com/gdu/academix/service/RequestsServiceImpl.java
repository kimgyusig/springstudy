package com.gdu.academix.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.academix.dto.DepartMentsDto;
import com.gdu.academix.dto.EmployeesDto;
import com.gdu.academix.dto.LeaveRequestDto;
import com.gdu.academix.dto.RanksDto;
import com.gdu.academix.dto.RequestsDto;
import com.gdu.academix.mapper.RequestsMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RequestsServiceImpl implements RequestsService {

  @Autowired
  private RequestsMapper requestsMapper;
  
  @Override
  @Transactional
  public int registerApproval(HttpServletRequest request) {
    int requestNo = insertRequest(request);
    insertLeaveRequest(request, requestNo);

    return requestNo;
  }
  
  
  @Override
  public int insertRequest(HttpServletRequest request) {
    
    String departName = request.getParameter("departName");
    String name = request.getParameter("name");
    String rankTitle = request.getParameter("rankTitle");
    String reason = request.getParameter("reason");
    int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));
    int picNo = Integer.parseInt(request.getParameter("picNo"));
    int requestStatus = Integer.parseInt(request.getParameter("requestStatus"));
    
    DepartMentsDto depart = new DepartMentsDto();
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
    
    return requests.getRequestNo();  
  }

  @Override
  public void insertLeaveRequest(HttpServletRequest request, int requestNo) {
    int duration = Integer.parseInt(request.getParameter("duration"));
    String startDateString = request.getParameter("startDate");
    String endDateString = request.getParameter("endDate");
    int leaveType = Integer.parseInt(request.getParameter("leaveType"));

    LocalDate startDate = LocalDate.parse(startDateString);
    LocalDate endDate = LocalDate.parse(endDateString);

    // LocalDate를 SQL Date로 변환
    Date sqlStartDate = Date.valueOf(startDate);
    Date sqlEndDate = Date.valueOf(endDate);

    LeaveRequestDto leaveRequest = LeaveRequestDto.builder()
                                                  .requestNo(requestNo)
                                                  .startDate(sqlStartDate)
                                                  .endDate(sqlEndDate)
                                                  .duration(duration)
                                                  .leaveType(leaveType)
                                                  .build();

    requestsMapper.insertLeaveRequest(leaveRequest);
  }

}
