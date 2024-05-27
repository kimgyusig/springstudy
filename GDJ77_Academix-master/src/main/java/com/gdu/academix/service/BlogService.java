package com.gdu.academix.service;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gdu.academix.dto.BlogDto;

public interface BlogService {
  ResponseEntity<Map<String, Object>> summernoteImageUpload(MultipartFile multipartFile);
  int registerBlog(HttpServletRequest request);
  ResponseEntity<Map<String, Object>> getBlogList(HttpServletRequest request);
  int updateHit(int blogNo);
  BlogDto getBlogByNo(int blogNo);
  List<String> getEditorImageList(String contents);
  int modifyBlog(HttpServletRequest request);
  int removeBlog(int blogNo);
  int registerComment(HttpServletRequest request);
  Map<String, Object> getCommentList(HttpServletRequest request);
  int registerReply(HttpServletRequest request);
  int removeComment(int commentNo);
  void removeBlogImageNotOnTheTable();
}
