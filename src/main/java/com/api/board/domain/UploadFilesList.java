package com.api.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@ToString
public class UploadFilesList {
    private List<UploadFiles> uploadFilesList;
}
