package com.gdu.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myapp.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/blog")
@RequiredArgsConstructor
@Controller
public class BlogController {
  private final BlogService blogService;

  @GetMapping("/list.page")
  public String list() {
    return "blog/list";
  }
  
  @GetMapping("/write.page")
  public String writePage() {
    return "blog/write";
  }
  
  @PostMapping(value="/summernote/imageUpload.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> summernoteImageUpload(@RequestParam("image") MultipartFile multipartFile) {
    return blogService.summernoteImageUpload(multipartFile);
  }
  
  @PostMapping("/register.do")
  public String register(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("insertCount", blogService.registerBlog(request));
    return "redirect:/blog/list.page";   // redirect 이동할때는 반드시 주소작성
  }
  
  @GetMapping(value="/getBlogList.do", produces = "application/json")
  public ResponseEntity<Map<String, Object>> getBlogList(HttpServletRequest request){
    return blogService.getBlogList(request);
  }
  
  @GetMapping("/updateHit.do")
  public String updateHit(@RequestParam int blogNo) {
    blogService.updateHit(blogNo);
    return "redirect:/blog/detail.do?blogNo=" + blogNo; // detail.do 에서 blogNo 파라미터로 넘긴다
  }
  
  @GetMapping("/detail.do")
  public String detail(@RequestParam int blogNo, Model model) {
    model.addAttribute("blog", blogService.getBlogByNo(blogNo));
    return "blog/detail";
  }
  
  @PostMapping(value="/registerComment.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> registerComment(HttpServletRequest request) {
    return new ResponseEntity<Map<String,Object>>(Map.of("insertCount", blogService.registerComment(request))
                                                         ,HttpStatus.OK);
    
    // return ResponseEntity.ok(Map.of("insertCount", blogService.registerComment(request)));
    
  }
  
  @GetMapping(value="/comment/list.do", produces = "application/json") // select 일때는 get방식
  public ResponseEntity<Map<String, Object>> commentList(HttpServletRequest request) {
    return new ResponseEntity<>(blogService.getCommentList(request)
                                    , HttpStatus.OK);
                                       
  }
  @PostMapping(value="/comment/registerReply.do", produces = "application/json")
  public ResponseEntity<Map<String, Object>> registerReply(HttpServletRequest request) {
    
    return ResponseEntity.ok(Map.of("insertReplyCount", blogService.registerReply(request)));
  }
  
  @GetMapping("/remove.do")
  public String removeBlog(@RequestParam int blogNo, Model model) {
    System.out.println("블로그번호" +blogNo);
      model.addAttribute("deletCount", blogService.removeBlog(blogNo));
      return "redirect:/blog/remove.do";
  }
  
}
