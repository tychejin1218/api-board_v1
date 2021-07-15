package com.api.board.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "boards")
public class Boards {

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