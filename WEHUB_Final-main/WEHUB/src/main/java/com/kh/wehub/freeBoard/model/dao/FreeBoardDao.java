package com.kh.wehub.freeBoard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.wehub.freeBoard.model.vo.Board;
import com.kh.wehub.freeBoard.model.vo.Reply;

@Mapper
public interface FreeBoardDao {
	
	List<Board> selectBoardList();
	
	Board selectBoardList(int boardNo);
	
	List<Board> selectBoardDetail(String keyword);

	int insertBoard(Board board);

	int updateBoard(Board board);

	int deleteBoard(int boardNo);

	List<Reply> selectBoardReplyList(Reply reply);

	int updateReply(@Param("replyContent")String replyContent,@Param("replyNo")int replyNo);

	int insertReply(Map<Object, Object> map);

	Reply selectReplyList(int replyNo);

	int deleteReply(@Param("replyNo") int replyNo);

	List<Board> selectBoardDetail();

	List<Board> infiniteScrollDown(int bnoToStart);

	List<Board> selectBoardDetail(int rownumdata);

	List<Board> selectBoardDetailKeyword(String keyword);

}
