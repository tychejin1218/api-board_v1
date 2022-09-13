package com.api.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
    private int commentId;
    private String content;
    private int board_seq;
}
