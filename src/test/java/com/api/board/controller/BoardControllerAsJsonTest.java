package com.api.board.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.api.board.domain.Board;
import com.api.board.service.BoardServiceTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardControllerAsJsonTest {
 
    Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);
 
    private MockMvc mockMvc;
 
    @Autowired
    WebApplicationContext webApplicationContext;
 
    public String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
 
    public <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
 
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        						 .addFilters(new CharacterEncodingFilter("UTF-8", true))
        						 .alwaysDo(print())
        						 .build();
    }
    
    int boardSeq = 0;
    
    /** 게시글 목록 조회 시 응답 값이 200이면 테스트 통과 */
    @Test
    public void testGetBoardList() {
 
    	try {
			
    		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board")
    									 .accept(MediaType.APPLICATION_JSON_VALUE))
				 					 	 .andReturn();
    			
    		assertEquals(200, mvcResult.getResponse().getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    /** 게시글 상세 조회 시 응답 값이 200이면 테스트 통과 */
    @Test
    public void testGetBoardDetail() {
        
    	try {
    		
    		if (boardSeq != 0) {
            	
            	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board/" + boardSeq)
                        					 .accept(MediaType.APPLICATION_JSON_VALUE))
                        					 .andReturn();

            	assertEquals(200, mvcResult.getResponse().getStatus());
            }
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    /** 게시글 등록 시 응답 값이 201이면 테스트 통과 */
    @Rollback(true)
    @Test
    public void testInsertBoard() throws Exception {
 
        Board insertBoard = new Board();
        insertBoard.setBoard_writer("게시글 작성자 등록");
        insertBoard.setBoard_subject("게시글 제목 등록");
        insertBoard.setBoard_content("게시글 내용 등록");
 
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/board")
        							 .contentType(MediaType.APPLICATION_JSON_VALUE)
        							 .content(this.mapToJson(insertBoard)))
        							 .andReturn();
 
        assertEquals(201, mvcResult.getResponse().getStatus());
    }
 
    /** 게시글 수정 시 응답 값이 200이면 테스트 통과 */
    @Rollback(true)
    @Test
    public void testUpdateBoard() {
        
    	try {
			
    		if (boardSeq != 0) {
    		     
            	Board updateBoard = new Board();
                updateBoard.setBoard_seq(boardSeq);
                updateBoard.setBoard_subject("게시글 제목 수정");
                updateBoard.setBoard_content("게시글 내용 수정");
         
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/board/" + boardSeq)
                							 .contentType(MediaType.APPLICATION_JSON_VALUE)
                							 .content(this.mapToJson(updateBoard)))
                							 .andReturn();
             
                assertEquals(200, mvcResult.getResponse().getStatus());
            } 
    		
		} catch (Exception e) {
			e.printStackTrace();
		}     
    }
 
    /** 게시글 삭제 시 응답 값이 200이면 테스트 통과 */
    @Rollback(true)
    @Test
    public void testDeleteBoard() { 
 
    	try {
    		
    		if (boardSeq != 0) {
        		
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/board/" + boardSeq))
                							 .andReturn();
         
                assertEquals(200, mvcResult.getResponse().getStatus());
        	}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}