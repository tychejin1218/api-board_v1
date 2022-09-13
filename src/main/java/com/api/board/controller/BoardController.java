package com.api.board.controller;
 
import com.api.board.domain.RequestVO;
import com.api.board.domain.UploadFiles;
import com.api.board.service.UploadService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.board.domain.Board;
import com.api.board.domain.Boards;
import com.api.board.exception.ResourceNotFoundException;
import com.api.board.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = "게시글 API : Board", description = "게시글 목록 조회, 상세 조회, 등록, 삭제, 수정 API")
@RequestMapping("/board")
@RestController
@Log4j2
public class BoardController {
 
    @Autowired
    private BoardService boardService;

    @Autowired
    private UploadService uploadService;
 
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
    public String  insertBoard(String jsonBoard, String uploadFilesList) throws Exception {

        Gson gson = new Gson();
        Board board = gson.fromJson(jsonBoard, Board.class );
        boardService.insertBoard(board);

        JSONArray jsonArray = jsonParsing(uploadFilesList);

        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            log.info("jsonObject : " + jsonObject);
            UploadFiles uploadFiles = gson.fromJson(String.valueOf(jsonObject), UploadFiles.class);
            uploadService.insertUpload(uploadFiles);
        }

        return "success";
    }



    public JSONArray jsonParsing(String param) throws Exception {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();

        jsonArray = (JSONArray) parser.parse(param);
        return jsonArray;
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