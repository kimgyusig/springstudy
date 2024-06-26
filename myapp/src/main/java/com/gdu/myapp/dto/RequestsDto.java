package com.gdu.myapp.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestsDto {

  private int requestNo, requestSort, requestStatus, picNo;
  private Date requestDate;
  private String reason, rejectreason;
  private EmployeesDto employees;
  
}
