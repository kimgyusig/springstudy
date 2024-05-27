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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String registerApproval(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int approvalCount = approvalService.insertApproval(request);
    int leaveApprovalCount = approvalService.insertLeaveApproval(request);
    
    // 두 서비스의 결과를 사용하여 적절한 처리 수행
    // 예를 들어, 리다이렉트 속성에 결과를 추가하거나 다른 작업 수행

    // 예시: 리다이렉트 속성에 결과 추가
    redirectAttributes.addFlashAttribute("approvalCount", approvalCount);
    redirectAttributes.addFlashAttribute("leaveApprovalCount", leaveApprovalCount);
    
    return "redirect:/approval/main.page";
  }
  
  @PostMapping("/list.do")
  public String prac(HttpServletRequest request) {
    
    return "redirect:/approval/main.page";
  }
  
}
