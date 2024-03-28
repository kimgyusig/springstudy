package com.gdu.mybatis_ex.dao;

import java.util.List;
import java.util.Map;

import com.gdu.mybatis_ex.dto.StudentDto;

public interface StudentDao {

//1. 목록
List<StudentDto> studentList();
//2. 전체 학생 수
int studentCount();
//3. 전체 점수 평균
double studentAverage();
//4. 삽입
int studentAdd(StudentDto student);
//5. 학생 조회
 List<StudentDto> queryStudentList(Map<String, Double> map);
//6. 조회된 학생 수
 int queryStudentCount(Map<String, Double> map);
// 7. 조회된 학생 점수 평균
double queryStudentAverage(Map<String, Double> map);
//8. 삭제
int studentDelete(int stuNo);
//9. 상세
StudentDto studentDetail(int stuNo);
//10. 수정
int studentModify(StudentDto student);
//11. TOP3
List<StudentDto> top3List();
  
}
