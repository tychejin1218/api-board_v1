package com.api.board.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "게시글 정보 : Board", description = "게시글 정보")
@XmlRootElement(name = "board")
@XmlType(propOrder = {"board_seq", "board_re_ref", "board_re_lev", "board_re_seq", "board_writer", "board_subject", "board_content", "board_hits", "del_yn", "ins_user_id", "ins_date", "upd_user_id", "upd_date"})
@Getter @Setter @ToString
public class Board {

	@ApiModelProperty(value = "게시글 번호")
    int board_seq;
    @ApiModelProperty(value = "게시글 그룹 번호")
    int board_re_ref;
    @ApiModelProperty(value = "게시글 답변 글의 깊이")
    int board_re_lev;
    @ApiModelProperty(value = "게시글 답변 글의 순서")
    int board_re_seq;
    @ApiModelProperty(value = "게시글 작성자")
    String board_writer;
    @ApiModelProperty(value = "게시글 제목")
    String board_subject;
    @ApiModelProperty(value = "게시글 내용")
    String board_content;
    @ApiModelProperty(value = "게시글 조회수")
    int board_hits;
    @ApiModelProperty(value = "게시글 삭제 유무")
    String del_yn;
    @ApiModelProperty(value = "게시글 입력자 ID")
    String ins_user_id;
    @ApiModelProperty(value = "게시글 입력 일시")
    String ins_date;
    @ApiModelProperty(value = "게시글 수정자 ID")
    String upd_user_id;
    @ApiModelProperty(value = "게시글 수정 일시")
    String upd_date;
}