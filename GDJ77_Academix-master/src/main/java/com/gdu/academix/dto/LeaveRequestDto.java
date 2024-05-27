package com.gdu.academix.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LeaveRequestDto {
  private int requestNo, duration, leaveType;
  private Date startDate, endDate;
}
