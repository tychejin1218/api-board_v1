package com.api.board.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.api.board.domain.Board;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {
 
    Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);
 
    @Autowired
    BoardService boardService;
 
    /** 게시글 목록 조회 시 NULL 아니면 테스트 통과 */
    @Test
	public void testGetBoardList() {
    	 try {
    		 
             List<Board> boardList = boardService.getBoardList();
             assertNotNull(boardList.size());
 			
         } catch (Exception e) {
             e.printStackTrace();
         }
	}

    /** 게시글 상세 조회 시 NULL이 아니면 테스트 통과 */
	@Test
	public void testGetBoardDetail() {
		
		try {
			
			List<Board> bookList = boardService.getBoardList();
			int boardSeq = bookList.get(0).getBoard_seq();
			
			Board bookDetail = boardService.getBoardDetail(boardSeq);
			assertNotNull(bookDetail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Rollback(true)
	@Test
	public void testInsertBoard() {
		
		try {

			Board board = new Board();
			board.setBoard_writer("게시글 작성자 등록");
			board.setBoard_subject("게시글 제목 등록");
			board.setBoard_content("게시글 내용 등록");			
			
			int result = boardService.insertBoard(board);			
			assertTrue(result == 1);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/** 게시글 수정 후 1이 응답되면 테스트 통과 */
	@Rollback(true)
	@Test
	public void testUpdateBoard() {
		
		try {
			
			List<Board> bookList = boardService.getBoardList();
			int boardSeq = bookList.get(0).getBoard_seq();

			Board board = new Board();
			board.setBoard_seq(boardSeq);
			board.setBoard_subject("게시글 제목 수정");
			board.setBoard_content("게시글 내용 수정");
			
			int result = boardService.updateBoard(board);
			assertTrue(result == 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시글 삭제 후 1이 응답되면 테스트 통과 */
	@Rollback(true)
	@Test
	public void testDeleteBoard() {

		try {
			
			List<Board> bookList = boardService.getBoardList();
			int boardSeq = bookList.get(0).getBoard_seq();
			
			int result = boardService.deleteBoard(boardSeq);			
			assertTrue(result == 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}