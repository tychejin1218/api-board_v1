package com.api.board.controller;
 
import java.util.List;

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
import com.api.board.service.BoardService;
 
@RequestMapping("/board")
@RestController
public class BoardController {
 
    @Autowired
    private BoardService boardService;
 
    /** 게시글 목록 조회 */
    @GetMapping
    public List<Board> getBoardList() throws Exception {
        return boardService.getBoardList();
    }
 
    /** 게시글 상세 조회 */
    @GetMapping("/{board_seq}")
    public Board getBoardDetail(@PathVariable("board_seq") int board_seq) throws Exception {
        return boardService.getBoardDetail(board_seq);
    }
 
    /** 게시글 등록  */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Board insertBoard(@RequestBody Board board) throws Exception {
 
        boardService.insertBoard(board);
 
        int boardSeq = board.getBoard_seq();
 
        Board boardDetail = boardService.getBoardDetail(boardSeq);
 
        return boardDetail;
    }
 
    /** 게시글 수정 */
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{board_seq}")
    public Board updateBoard(@PathVariable("board_seq") int board_seq, @RequestBody Board board) throws Exception {
 
        boardService.updateBoard(board);
 
        Board boardDetail = boardService.getBoardDetail(board_seq);
 
        return boardDetail;
    }
 
    /** 게시글 삭제  */
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{board_seq}")
    public Board deleteBoard(@PathVariable("board_seq") int board_seq) throws Exception {
 
        boardService.deleteBoard(board_seq);
 
        Board deleteBoard = new Board();
        deleteBoard.setBoard_seq(board_seq);
 
        return deleteBoard;
    }
}