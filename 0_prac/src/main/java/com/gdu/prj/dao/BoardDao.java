package com.gdu.prj.dao;

import java.util.List;

import com.gdu.prj.dto.BoardDto;

public interface BoardDao {
  List<BoardDto> getBoardlist();
  BoardDto getBoardByNo(int boardNo);

}
