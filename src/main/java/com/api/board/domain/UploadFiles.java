package com.api.board.domain;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@ApiModel(value = "첨부파일 정보 : Attach", description = "첨부파일 정보")
@Data
public class UploadFiles {
    @ApiModelProperty(value = "첨부파일 번호")
    private int img_seq;
    @ApiModelProperty(value = "첨부파일 이름")
    private String fileName;
    @ApiModelProperty(value = "첨부파일 UUID")
    private String uuid;
    @ApiModelProperty(value = "첨부파일 폴더경로")
    private String folderPath;


    public UploadFiles() {

    }

    public UploadFiles(String fileName, String uuid, String folderPath) {
        this.fileName = fileName;
        this.uuid = uuid;
        this.folderPath = folderPath;
    }

    @ApiModelProperty(value = "첨부파일 경로")
    public String getImageURL() {
        try{
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @ApiModelProperty(value = "첨부파일썸네일 경로")
    public String getThumbnailURL() {
        try{
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String toString(){
        return "UploadFiles[img_seq=" + img_seq + ", fileName=" + fileName + ", uuid=" + uuid + ", folderPath=" + folderPath + "]";
    }


}
