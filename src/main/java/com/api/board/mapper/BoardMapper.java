package com.api.board.mapper;

import java.util.List;

import com.api.board.domain.Board;

public interface BoardMapper {
	
	/** 게시글 목록 조회 */
    public List<Board> getBoardList() throws Exception;
}