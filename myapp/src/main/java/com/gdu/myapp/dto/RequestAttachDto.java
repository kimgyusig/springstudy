package com.gdu.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestAttachDto {

  private int attachNo, requestNo;
  private String uploadPath, filesystemName, originalFilename;
  
}
