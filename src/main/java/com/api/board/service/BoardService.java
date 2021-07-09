package com.api.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.board.domain.Board;
import com.api.board.mapper.BoardMapper;

@Service
public class BoardService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardMapper boardMapper;

	/** 게시글 목록 조회 */
	public List<Board> getBoardList() throws Exception {
		return boardMapper.getBoardList();
	}
}