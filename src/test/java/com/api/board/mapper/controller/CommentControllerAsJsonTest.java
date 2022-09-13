package com.api.board.mapper.controller;

import com.api.board.domain.Board;
import com.api.board.domain.Comment;
import com.api.board.domain.Criteria;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentControllerAsJsonTest {

    private int commentId = 1;
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
    

    /** 댓글 리스트 조회 시 응답 값이 200이면 테스트 통과 */
    @Test
    public void testGetCommentList() {

    	try {
            Criteria cri = new Criteria();

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/comment/list")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(this.mapToJson(cri)))
                            .andReturn();

    		assertEquals(200, mvcResult.getResponse().getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /** 댓글 상세 조회 시 응답 값이 200이면 테스트 통과 */
    @Test
    public void testGetCommentDetail() {

    	try {

    		if (commentId != 0) {

            	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/comment/" + commentId)
                        					 .accept(MediaType.APPLICATION_JSON_VALUE))
                        					 .andReturn();

            	assertEquals(200, mvcResult.getResponse().getStatus());
            }

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    /** 등록 시 응답 값이 200이면 테스트 통과 */
    @Rollback(true)
    @Test
    public void testInsertComment() throws Exception {

        for (int i = 0; i <= 100; i++){
            Comment comment = new Comment();
            comment.setContent("test"+i);

            if(i <= 25){
                comment.setBoard_seq(96);
            } else if (i <= 50) {
                comment.setBoard_seq(97);
            } else if (i <= 75) {
                comment.setBoard_seq(98);
            } else {
                comment.setBoard_seq(99);
            }

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/comment")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(this.mapToJson(comment)))
                    .andReturn();

            assertEquals(200, mvcResult.getResponse().getStatus());

        }

 

    }
 
    /** 댓글 수정 시 응답 값이 200이면 테스트 통과 */
    @Test
    public void testUpdateComment() {

    	try {

    		if (commentId != 0) {

            	Comment comment = new Comment();
                comment.setCommentId(commentId);
                comment.setContent("content update");

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/comment/" + commentId)
                							 .contentType(MediaType.APPLICATION_JSON_VALUE)
                							 .content(this.mapToJson(comment)))
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

    		if (commentId != 0) {

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/comment/" + commentId))
                							 .andReturn();

                assertEquals(200, mvcResult.getResponse().getStatus());
        	}

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}