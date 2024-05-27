package com.gdu.myapp.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.myapp.dto.DepartmentsDto;
import com.gdu.myapp.dto.EmployeesDto;
import com.gdu.myapp.dto.LeaveRequestDto;
import com.gdu.myapp.dto.RequestsDto;
import com.gdu.myapp.service.ApprovalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {
  
  private final ApprovalService approvalService;
  

  @GetMapping("/main.page")
  public String approvalMain() {
    return "/approval/main";
  }
  
  @GetMapping("/write.page")
  public String writePage() {
    return"/approval/write";
  }
  
  @PostMapping("/write.do")
  public int registerApproval(Model model, HttpServletRequest request) {
      // 첫 번째 메서드 호출
      int result1 =  approvalService.insertApproval(request);
      
      // 두 번째 메서드 호출
      int result2 = approvalService.insertLeaveApproval(request);
      
      // 두 결과를 합산하여 반환하거나, 조정하여 반환
      return result1 + result2;
  }
  
}
