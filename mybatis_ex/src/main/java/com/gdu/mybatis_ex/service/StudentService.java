package com.gdu.mybatis_ex.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.gdu.mybatis_ex.dto.StudentDto;

public interface StudentService {

  int studentAdd(StudentDto student) throws IOException;
   StudentDto studentDetail(int studentNo);
    Map<String, Object> studentQuery(Map<String, Object> map);
    List<StudentDto> studentList(StudentDto student);
   int studentModify(StudentDto student) throws IOException;
   int studentDelete(int student) throws IOException;
  
}
