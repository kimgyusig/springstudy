package com.gdu.prj06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MvcController {

 
  
  @GetMapping(value= {"/", "/main.do"})
  public String welcom() {
    return "index";
  }
  
 
  
}