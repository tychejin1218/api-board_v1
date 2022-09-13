package com.api.board.service.Impl;

import com.api.board.domain.Comment;
import com.api.board.domain.Criteria;
import com.api.board.mapper.CommentMapper;
import com.api.board.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper mapper;

    @Override
    public int register(Comment comment) {
        log.info("register.............." + comment);
        return mapper.insert(comment);
    }

    @Override
    public Comment get(int commentId) {
        log.info("get.............." + commentId);
        return mapper.read(commentId);
    }

    @Override
    public List<Comment> getList(Criteria cri) {
        log.info("get List with criteria : .........." + cri);
        return mapper.getListWithPaging(cri);
    }

    @Override
    public int modify(Comment comment) {
        log.info("modify................." + comment);
        return mapper.update(comment);
    }

    @Override
    public boolean remove(int commentId) {
        log.info("remove................" + commentId);
        return mapper.delete(commentId) == 1;
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");
        return mapper.getTotalCount(cri);
    }
}
