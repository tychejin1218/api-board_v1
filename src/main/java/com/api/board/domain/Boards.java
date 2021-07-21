package com.api.board.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "게시글 목록 : Boards", description = "게시글 목록")
@XmlRootElement(name = "boards")
public class Boards {

	@ApiModelProperty(value = "게시글 목록")
	private List<Board> boards;
	
	public Boards() {
    }
 
    public Boards(List<Board> boards) {
        setBoards(boards);
    }
 
    @XmlElement(name = "board")
    public List<Board> getBoards() {
        return boards;
    }
 
    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}