package com.api.board.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "게시글, 첨부파일 정보")
public class RequestVO {
    @ApiModelProperty(value = "게시글 정보")
    private Board board;
    @ApiModelProperty(value = "첨부파일 정보")
    private List<UploadFiles> uploadFilesList;
}
