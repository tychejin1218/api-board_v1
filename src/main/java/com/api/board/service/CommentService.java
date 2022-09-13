package com.api.board.service;


import com.api.board.domain.Comment;
import com.api.board.domain.Criteria;

import java.util.List;

public interface CommentService {

    public int register (Comment comment);

    public Comment get (int commentId);

    public List<Comment> getList (Criteria cri);

    public int modify (Comment comment);

    public boolean remove (int commentId);

    public int getTotal (Criteria cri);
}
