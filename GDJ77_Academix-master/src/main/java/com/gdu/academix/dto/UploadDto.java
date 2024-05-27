package com.gdu.academix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UploadDto {
  private int uploadNo;
  private String title, contents, createDt, modifyDt;
  private int attachCount;
  private UserDto user;
}
