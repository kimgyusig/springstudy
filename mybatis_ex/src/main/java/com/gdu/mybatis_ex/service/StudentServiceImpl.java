package com.gdu.mybatis_ex.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gdu.mybatis_ex.dao.StudentDao;
import com.gdu.mybatis_ex.dto.StudentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

  private final StudentDao studentDao;
  
  @Override
  public int studentAdd(StudentDto student) throws IOException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public StudentDto studentDetail(int studentNo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, Object> studentQuery(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<StudentDto> studentList(StudentDto student) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int studentModify(StudentDto student) throws IOException {
   int insertCount = studentDao.studentAdd(student);
    return insertCount;
  }

  @Override
  public int studentDelete(int student) throws IOException {
    // TODO Auto-generated method stub
    return 0;
  }

}
