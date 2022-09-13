package com.api.board.mapper.controller;
 
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.api.board.domain.Board;
import com.api.board.service.BoardServiceTest;
 
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardControllerAsXmlTest {
 
    Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);
 
    private MockMvc mockMvc;
 
    @Autowired
    WebApplicationContext webApplicationContext;
 
    @Autowired
    Jaxb2Marshaller jaxb2Marshaller;
 
    public String mapToXml(Object obj) throws Exception {
        StringWriter writer = new StringWriter();
        jaxb2Marshaller.marshal(obj, new StreamResult(writer));
        String content = writer.toString();
        return content;
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
					 .accept(MediaType.APPLICATION_XML))
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
                        					 .accept(MediaType.APPLICATION_XML))
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
        							 .contentType(MediaType.APPLICATION_XML)
        							 .content(this.mapToXml(insertBoard)))
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
                							 .contentType(MediaType.APPLICATION_XML)
                							 .content(this.mapToXml(updateBoard)))
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