package com.api.board.service;

import com.api.board.domain.Board;
import com.api.board.domain.UploadFiles;

import java.util.List;

public interface UploadService {

    public List<UploadFiles> getUploadList(int board_seq) throws Exception;

    public UploadFiles getUploadDetail(int img_seq) throws Exception;

    /** 게시글 등록 */
    public int insertUpload(UploadFiles uploadFiles) throws Exception;

    public List<UploadFiles> getOldFiles() throws Exception;


}
