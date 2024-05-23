package com.gdu.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

  @GetMapping("/main.page")
  public String approvalMain() {
    return "/approval/main";
  }
  
  @GetMapping("/write.page")
  public String writePage() {
    return"/approval/write";
  }
  
}
