package com.gdu.academix.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.academix.service.BbsService;
import com.gdu.academix.service.RequestsService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/approval")
@RequiredArgsConstructor
@Controller
public class ApprovalController {
  
  @Autowired
  private RequestsService requestsService;
  
  @GetMapping("/main.page")
  public String approvalMain() {
    return "approval/main";
  }
  
  @GetMapping("/write.page")
  public String write() {
	  return "approval/write";
  }
  
  @PostMapping("/write.do")
  public String registerApproval(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    try {
      int requestNo = requestsService.registerApproval(request);
      redirectAttributes.addFlashAttribute("message", "Approval registered successfully with requestNo: " + requestNo);
  } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Failed to register approval");
  }
  return "redirect:/approval/main.page";
  }
  

}
