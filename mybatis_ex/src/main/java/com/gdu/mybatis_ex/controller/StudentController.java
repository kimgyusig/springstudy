package com.gdu.mybatis_ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.mybatis_ex.dto.StudentDto;
import com.gdu.mybatis_ex.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value="/student")
@RequiredArgsConstructor
@Controller
public class StudentController {

  private final StudentService studentService;
  
 @GetMapping(value="/list.do")
 public String list() {
   return "/student/list";
 }
  
 @GetMapping(value="/write.do")
 public String write() {
    
   return"/student/write";
 }
 
 @PostMapping(value="/register.do")
 public String register(StudentDto student) throws Exception{
   int insertCount = studentService.studentModify(student);
   
   return "redirect:/register.do";
 }
 
}
