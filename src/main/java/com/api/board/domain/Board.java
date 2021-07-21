package com.api.board.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "게시글 정보 : Board", description = "게시글 정보")
@XmlRootElement(name = "board")
@XmlType(propOrder = {"board_seq", "board_re_ref", "board_re_lev", "board_re_seq", "board_writer", "board_subject", "board_content", "board_hits", "del_yn", "ins_user_id", "ins_date", "upd_user_id", "upd_date"})
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

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public int getBoard_re_ref() {
		return board_re_ref;
	}

	public void setBoard_re_ref(int board_re_ref) {
		this.board_re_ref = board_re_ref;
	}

	public int getBoard_re_lev() {
		return board_re_lev;
	}

	public void setBoard_re_lev(int board_re_lev) {
		this.board_re_lev = board_re_lev;
	}

	public int getBoard_re_seq() {
		return board_re_seq;
	}

	public void setBoard_re_seq(int board_re_seq) {
		this.board_re_seq = board_re_seq;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_subject() {
		return board_subject;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getBoard_hits() {
		return board_hits;
	}

	public void setBoard_hits(int board_hits) {
		this.board_hits = board_hits;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getIns_user_id() {
		return ins_user_id;
	}

	public void setIns_user_id(String ins_user_id) {
		this.ins_user_id = ins_user_id;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getUpd_user_id() {
		return upd_user_id;
	}

	public void setUpd_user_id(String upd_user_id) {
		this.upd_user_id = upd_user_id;
	}

	public String getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
}