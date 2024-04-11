package com.gdu.myapp.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
  public boolean registerUpload(MultipartHttpServletRequest multipartRequest);
  public void loadUploadList(Model model);
  void loadUploadByNo(int uploadNo, Model model);
  
}
