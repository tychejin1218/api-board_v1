package com.api.board.controller;

import com.api.board.domain.Comment;
import com.api.board.domain.CommentList;
import com.api.board.domain.Criteria;
import com.api.board.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Log4j2
public class CommentController {

    @Autowired
    CommentService service;

    // 생성
    @PostMapping
    public ResponseEntity<String> creatComment (@RequestBody Comment comment) {
        log.info("comment : " + comment);

        return service.register(comment) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<CommentList> getCommentList (Criteria cri) {
        log.info("list : " + cri);
        List<Comment> comments = service.getList(cri);

        int total = service.getTotal(cri);
        log.info("total : " + total);

        CommentList commentList = new CommentList();
        commentList.setCommentList(comments);
        commentList.setTotal(total);

        return new ResponseEntity<>(commentList, HttpStatus.OK);

    }

    // 상세 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentDetail (@PathVariable ("commentId") int commentId) {
        log.info("commentId : " + commentId);
        return new ResponseEntity<>(service.get(commentId), HttpStatus.OK);
    }

    // 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<String> modifyComment (@RequestBody Comment comment, @PathVariable ("commentId") int commentId) {
        comment.setCommentId(commentId);
        log.info("commentId : " + commentId);
        log.info("comment : " + comment);

        return service.modify(comment) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // 삭제
    @DeleteMapping("/{commentId}")

    public ResponseEntity<String> removeCommnet (@PathVariable ("commentId") int commentId) {
        log.info("remove : " + commentId);

        return service.remove(commentId)? new ResponseEntity<>("success", HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
