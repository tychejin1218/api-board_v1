package com.api.board.mapper;

import com.api.board.domain.Board;
import com.api.board.domain.UploadFiles;

import java.util.List;

public interface UploadMapper {

    public List<UploadFiles> getUploadList(int board_seq) throws Exception;

    public UploadFiles getUploadDetail(int img_seq) throws Exception;

    public int insertUpload(UploadFiles uploadFiles) throws Exception;

    public List<UploadFiles> getOldFiles();
}
