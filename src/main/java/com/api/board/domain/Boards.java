package com.api.board.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "게시글 목록 : Boards", description = "게시글 목록")
@XmlRootElement(name = "boards")
@Getter @Setter @ToString
public class Boards {

	@ApiModelProperty(value = "게시글 목록")
	private List<Board> boards;
}