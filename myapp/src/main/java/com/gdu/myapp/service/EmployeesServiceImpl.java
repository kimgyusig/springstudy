package com.gdu.myapp.service;

import org.springframework.stereotype.Service;

import com.gdu.myapp.dto.EmployeesDto;
import com.gdu.myapp.mapper.EmployeesMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {

  private  final EmployeesMapper employeesMapper;
  
  @Override
  public int insertEmployees(EmployeesDto employees) {
    return employeesMapper.insertEmployees(employees);
  }

}
