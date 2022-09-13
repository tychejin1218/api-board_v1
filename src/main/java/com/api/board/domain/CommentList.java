package com.api.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CommentList {
    private List<Comment> commentList;
    private int total;
}
