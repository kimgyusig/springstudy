package com.gdu.prj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardDao boardDao;
  
  @Override
  public List<BoardDto> getBoardList() {
    return boardDao.getBoardlist();
  }

  @Override
  public BoardDto getByboardNo(int boardNo) {
    return boardDao.getBoardByNo(boardNo);
  }

}
