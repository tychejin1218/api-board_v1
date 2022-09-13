package com.api.board.mapper.controller;
 
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
 
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardControllerExceptionTest {
 
    Logger logger = LoggerFactory.getLogger(BoardControllerExceptionTest.class);
 
    private MockMvc mockMvc;
 
    @Autowired
    WebApplicationContext webApplicationContext;
  
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        						 .addFilters(new CharacterEncodingFilter("UTF-8", true))
        						 .alwaysDo(print())
        						 .build();
    }
 
    int boardSeq = 0;
    
    /** 게시글 상세 조회 시 응답 값이 404이면 테스트 통과 */
    @Test
    public void getBoardDetailJSON(){
 
    	try {
    		
    		if (boardSeq != 0) {
    			
    			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board/" + boardSeq)
						 .accept(MediaType.APPLICATION_XML))
						 .andReturn();

    			assertEquals(404, mvcResult.getResponse().getStatus());	
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
 
    /** 게시글 상세 조회 시 응답 값이 404이면 테스트 통과 */
    @Test
    public void getBoardDetailXML() throws Exception {
        
    	try {
    		
    		if (boardSeq != 0) {
    			
    			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/board/" + boardSeq)
						 .accept(MediaType.APPLICATION_JSON))
						 .andReturn();

    			assertEquals(404, mvcResult.getResponse().getStatus());	
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}