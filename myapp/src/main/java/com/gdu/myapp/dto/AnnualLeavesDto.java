package com.gdu.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AnnualLeavesDto {

  private int leavesNo, remainLeaves, totalLeaves, employeeNo;
  
}
