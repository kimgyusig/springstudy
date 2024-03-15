package com.gdu.prj.service;

import java.util.List;

import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dto.BoardDto;

public interface BoardService {
   List<BoardDto> getBoardList();
   BoardDto getByboardNo(int boardNo);
}
