package com.gdu.myapp.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.myapp.service.UploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RemoveTempFilesScheduler {
  private final UploadService uploadService;

  // 12시 20분에 removeTempFiles 서비스 동작하는 스케줄러
  @Scheduled(cron="0 25 12 * * ?")
  public void execute() {
  
    uploadService.removeTempFiles();
    
  }
  
}
