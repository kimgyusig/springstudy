package com.gdu.prj.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.prj.dto.BoardDto;
import com.gdu.prj.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController1 {
  
  private final BoardService boardService;
  
  @ResponseBody
  @GetMapping("board/list.do")
  public List<BoardDto> list(){
    return boardService.getBoardList();
  }

}
