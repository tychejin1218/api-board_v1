package com.api.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.board.domain.Board;
import com.api.board.service.BoardService;

@RequestMapping(value = "/board")
@RestController
public class BoardController {

	@Autowired
    private BoardService boardService;
    
    @GetMapping
    public List<Board> getBoardList() throws Exception {
        return boardService.getBoardList();
    }
}