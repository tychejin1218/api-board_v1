package com.api.board.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.board.domain.Board;
import com.api.board.domain.Boards;
import com.api.board.exception.ResourceNotFoundException;
import com.api.board.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "게시글 관련한 API : /board")
@RequestMapping("/board")
@RestController
public class BoardController {
 
    @Autowired
    private BoardService boardService;
 
    /** 게시글 목록 조회 */
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회합니다.")
    @GetMapping
    public Boards getBoardList() throws Exception {
        
    	Boards boards = new Boards();
    	boards.setBoards(boardService.getBoardList());
    	
    	return boards;
    }
 
    /** 게시글 상세 조회 */
    @ApiOperation(value = "게시글 상세 조회", notes = "게시글를 상세 조회합니다.")
    @GetMapping("/{board_seq}")
    public Board getBoardDetail(@PathVariable("board_seq") int board_seq) throws Exception {
        
    	Board board = boardService.getBoardDetail(board_seq);
        
        if(board == null) {
        	throw new ResourceNotFoundException();
        }
        
    	return board; 
    }
 
    /** 게시글 등록  */
    @ApiOperation(value = "게시글 등록", notes = "게시글을 등록합니다.")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Board insertBoard(@RequestBody Board board) throws Exception {
 
        boardService.insertBoard(board);
 
        int boardSeq = board.getBoard_seq();
 
        Board boardDetail = boardService.getBoardDetail(boardSeq);
 
        return boardDetail;
    }
 
    /** 게시글 수정 */
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{board_seq}")
    public Board updateBoard(@PathVariable("board_seq") int board_seq, @RequestBody Board board) throws Exception {
 
        boardService.updateBoard(board);
 
        Board boardDetail = boardService.getBoardDetail(board_seq);
 
        return boardDetail;
    }
 
    /** 게시글 삭제 */
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{board_seq}")
    public Board deleteBoard(@PathVariable("board_seq") int board_seq) throws Exception {
 
        boardService.deleteBoard(board_seq);
 
        Board deleteBoard = new Board();
        deleteBoard.setBoard_seq(board_seq);
 
        return deleteBoard;
    }
}