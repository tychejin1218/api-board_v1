package com.api.board.mapper;

import com.api.board.domain.Comment;
import com.api.board.domain.Criteria;

import java.util.List;

public interface CommentMapper {

    public int insert (Comment comment);

    public Comment read (int commentId);

    public int delete (int commentId);

    public int update (Comment comment);

    public List<Comment> getListWithPaging (Criteria cri);

    public int getTotalCount (Criteria cri);
}
