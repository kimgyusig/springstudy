package com.gdu.prj04.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.prj04.dao.BoardDao;


@Controller
public class MvcController {

  @Inject
  private BoardDao boardDao;
  
  @GetMapping(value= {"/", "/main.do"})
  public String welcom() {
    
    System.out.println(boardDao.getBoardList());
    return "index";
  }
  
  @GetMapping("/exercise1.do") // void 사용해 .jsp 로
  public void exercies1() {
    
  }
  
  @GetMapping("/exercise2.do")
  public String exercise2() {
    return "exercise2";
  }
  @GetMapping("/exercise3.do")
  public String exercise3() {
    return "exercise3";
  }
  
}
