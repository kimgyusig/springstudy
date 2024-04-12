package com.gdu.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myapp.dto.AttachDto;
import com.gdu.myapp.dto.UploadDto;
import com.gdu.myapp.service.UploadService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/upload")
@RequiredArgsConstructor
@Controller
public class UploadController {

  private final UploadService uploadService;
  
  @GetMapping("/list.do")
  public String list(HttpServletRequest request, Model model) {
    model.addAttribute("request", request);
    uploadService.loadUploadList(model);
    return "upload/list";
  }
  
  @GetMapping("/write.page")
  public String write() {
    return "upload/write";
  }
  
  @PostMapping("/register.do")
  public String register(MultipartHttpServletRequest multipartrequest
                          , RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("inserted", uploadService.registerUpload(multipartrequest));
    return "redirect:/upload/list.do";
  }
  
  @GetMapping("/detail.do")
  public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo
                     , Model model) {
    uploadService.loadUploadByNo(uploadNo, model);
    return "upload/detail";
  }
  
  @GetMapping("/download.do")
  public ResponseEntity<Resource> download(HttpServletRequest request) {
    return uploadService.download(request);
  }
  
  @GetMapping("/downloadAll.do")
  public ResponseEntity<Resource> downloadAll(HttpServletRequest request) {
    return uploadService.downloadAll(request);
  }
  
  @PostMapping("/edit.do")
  public String edit(@RequestParam int uploadNo, Model model) {
    model.addAttribute("upload", uploadService.getUploadByNo(uploadNo));
    return "upload/edit";
  }
  
  @PostMapping("/modify.do")
  public String modify(UploadDto upload, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("updateCount", uploadService.modifyUpload(upload));
    // return "redirect:/upload/list.do";
    return "redirect:/upload/detail.do?uploadNo=" + upload.getUploadNo(); // 수정 완료 후 상세 보기로 이동
  }
  
  @GetMapping(value="/attachList.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> attachList(@RequestParam int uploadNo) {
    return uploadService.getAttachList(uploadNo);
  }
  
  @PostMapping(value="/removeAttach.do", produces = "application/json")
  public ResponseEntity<Map<String, Object>> removeAttach(@RequestBody AttachDto attach) {
    return uploadService.removeAttach(attach.getAttachNo());
  }
  @GetMapping(value="/removeUpload.do")
  public String removeUpload(@RequestParam int uploadNo, RedirectAttributes redirectAttributes ) {
    redirectAttributes.addFlashAttribute("deleteCount", uploadService.removeUpload(uploadNo));
    return "redirect:/upload/list.do";
  }
  
}
