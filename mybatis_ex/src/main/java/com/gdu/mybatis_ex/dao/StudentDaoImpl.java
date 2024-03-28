package com.gdu.mybatis_ex.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.gdu.mybatis_ex.dto.StudentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {

  private final SqlSessionTemplate sqlSessionTemplate;
  private final static String NS = "com.gdu.mybatis_ex.mybatis.mapper.student_t.";
  
  @Override
  public List<StudentDto> studentList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int studentCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double studentAverage() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int studentAdd(StudentDto student) {
    return sqlSessionTemplate.insert(NS + "studentAdd", student);
  }

  @Override
  public List<StudentDto> queryStudentList(Map<String, Double> map) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int queryStudentCount(Map<String, Double> map) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double queryStudentAverage(Map<String, Double> map) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int studentDelete(int stuNo) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public StudentDto studentDetail(int stuNo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int studentModify(StudentDto student) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<StudentDto> top3List() {
    // TODO Auto-generated method stub
    return null;
  }

}
