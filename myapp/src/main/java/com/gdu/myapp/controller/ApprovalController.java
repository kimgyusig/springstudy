package com.gdu.myapp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
  public int registerApproval(Map<String, Object> map ) {

    return approvalService.insertApproval(map);
  }
  
}
