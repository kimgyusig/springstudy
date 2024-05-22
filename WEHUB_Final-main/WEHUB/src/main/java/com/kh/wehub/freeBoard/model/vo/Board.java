package com.kh.wehub.freeBoard.model.vo;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int boardNo;
	
	private String userId;
	
	private int boardWriterNo;
	
	private String userName;

	private String boardContent;

	private String status;

	private List<Reply> replies; 
	
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date boardCreateDate;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date boardModifyDate;
	
	private String memberImage;
	
	private String dept_name;
	
	private int rownum;
}
