package com.api.board.service.Impl;

import com.api.board.domain.Board;
import com.api.board.domain.UploadFiles;
import com.api.board.mapper.UploadMapper;
import com.api.board.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadMapper uploadMapper;

    @Override
    public List<UploadFiles> getUploadList(int board_seq) throws Exception {
        return uploadMapper.getUploadList(board_seq);
    }

    @Override
    public UploadFiles getUploadDetail(int img_seq) throws Exception {
        return uploadMapper.getUploadDetail(img_seq);
    }

    @Override
    public int insertUpload(UploadFiles uploadFiles) throws Exception {
        return uploadMapper.insertUpload(uploadFiles);
    }

    @Override
    public List<UploadFiles> getOldFiles() throws Exception {
        return uploadMapper.getOldFiles();
    }


}
