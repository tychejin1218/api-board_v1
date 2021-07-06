package com.api.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.board.domain.Board;
import com.api.board.mapper.BoardMapper;

@Service
public class BoardService {
 
    @Autowired
    private BoardMapper boardMapper;
 
    /** 게시글 목록 조회 */
    public List<Board> getBoardList() throws Exception {
        return boardMapper.getBoardList();
    }
}