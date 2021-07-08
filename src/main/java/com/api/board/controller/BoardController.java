package com.api.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.board.domain.Board;
import com.api.board.service.BoardService;

@RequestMapping(value = "/board")
@RestController
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;

	@GetMapping
	public List<Board> getBoardList() throws Exception {
		
		logger.info("===== ===== ===== logging.level.com.api.board.controller - Logger Level ===== ===== =====");
        logger.trace("Logger Level - [TRACE]"); 
        logger.debug("Logger Level - [DEBUG]"); 
        logger.info("Logger Level - [INFO]"); 
        logger.warn("Logger Level - [WARN]"); 
        logger.error("Logger Level - [ERROR]");
        logger.info("===== ===== ===== logging.level.com.api.board.controller - Logger Level ===== ===== =====");
		
		return boardService.getBoardList();
	}
}