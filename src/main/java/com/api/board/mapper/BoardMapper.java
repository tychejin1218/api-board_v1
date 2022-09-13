package com.api.board.mapper;

import java.util.List;

import com.api.board.domain.Board;

public interface BoardMapper {
	
	/** 게시글 목록 조회 */
    public List<Board> getBoardList() throws Exception;
    
    /** 게시글 상세 조회 */
    public Board getBoardDetail(int board_seq) throws Exception;
    
    /** 게시글 등록 */
    public int insertBoard(Board board) throws Exception;  
    
    /** 게시글 수정 */
    public int updateBoard(Board board) throws Exception;
    
    /** 게시글 삭제 */
    public int deleteBoard(int board_seq) throws Exception;


}