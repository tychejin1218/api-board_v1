package com.api.board.controller;

import com.api.board.domain.UploadFiles;
import com.api.board.domain.UploadFilesList;
import com.api.board.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/boards")
@Log4j2
@Api(tags = "업로드 API : Attach", description = "첨부파일 업로드, 첨부파일 조회, 첨부파일 삭제")
public class UploadController {


    @Autowired
    private UploadService uploadService;


    @Value("${upload.path}")
    private String uploadPath;


    @ApiOperation(value = "첨부파일 업로드", notes = "첨부파일을 업로드 합니다.")
    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadFiles>> upload(@RequestParam MultipartFile[] uploadFiles)
            throws Exception {

        List<UploadFiles> resultUploadList = new ArrayList<>();

        for (MultipartFile file : uploadFiles) {
            if (file.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String originalName = file.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);

            log.info("originalName : " + originalName);
            log.info("fileName " + fileName);


            String folderPath = makeFoler();
            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            Path savaPath = Paths.get(saveName);

            try {
                // 원본 파일 저장
                file.transferTo(savaPath);
                // 섬네일 생성 (섬네일 파일 이름은 중간에 "s_"로 시작하도록)
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator
                        + "s_" + uuid + "_" + fileName;
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savaPath.toFile(), thumbnailFile,100,100);
                UploadFiles attachDTO = new UploadFiles(fileName, uuid, folderPath);
                resultUploadList.add(attachDTO);
            } catch (IOException e) {
                e.printStackTrace();

            }
        } // end for
        return new ResponseEntity<>(resultUploadList, HttpStatus.OK);
    }

    private String makeFoler() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderpath = str.replace("/", File.separator);

        // make folder
        File uploadPathFoler = new File(uploadPath, folderpath);
        // File newFile = new File(dir, "파일명");
        // -> 부모 디렉토리를 파라미터로 인스턴스 생성

        if(uploadPathFoler.exists() == false) {
            uploadPathFoler.mkdirs();
            // 만약 uploadPathFolder가 존재하지 않는다면 makeDirectory하라는 의미
            // mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
            // mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
        }
        return folderpath;
    }


    @ApiOperation(value = "첨부파일 조회", notes = "첨부파일을 보여줍니다.")
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFiles(String fileName) {
        ResponseEntity<byte[]> result = null;

        try{
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
            log.info("filename : " + srcFileName);
            File file = new File(uploadPath + File.separator + srcFileName);
            log.info("file : " + file);
            HttpHeaders header = new HttpHeaders();

            // MIME 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // File 객체를 Path로 변환하여 MIME 타입을 판단하여 HTTPHeaders의 Content-Type에 값으로 들어갑니다.

            // 파일 데이터 처리 *FileCopyUtils.copy 아래에 정리
            // new ResponseEntity(body, header, status)
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  result;
    }

    @ApiOperation(value = "첨부파일을 삭제", notes = "첨부파일을 삭제합니다.")
    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName) {

        String srcFileName = null;

        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("delete:" + srcFileName);
            // UUID가 포함된 파일이름을 디코딩해줍니다.
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();
            log.info(result);

            File thumbnail = new File(file.getParent(), "s_"+file.getName());
            // getParent() - 현재 File 객체가 나타내는 파일의 디렉토리의 부모 디렉토리의 이름을 String 으로 리턴해준다.
            result = thumbnail.delete();
            return  new ResponseEntity<>(result, HttpStatus.OK);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/uploadList/{board_seq}")
    public ResponseEntity<UploadFilesList> getUploadFilesList (@PathVariable String board_seq) throws Exception {
        UploadFilesList uploadFilesList = new UploadFilesList();
        uploadFilesList.setUploadFilesList(uploadService.getUploadList(Integer.parseInt(board_seq)));
        return new ResponseEntity<>(uploadFilesList, HttpStatus.OK);
    }
    
    @GetMapping("/download/{img_seq}")
    public ResponseEntity<Resource> download(@PathVariable int img_seq) throws Exception {
        UploadFiles uploadFiles = uploadService.getUploadDetail(img_seq);
        UrlResource resource = new UrlResource("file:" + uploadPath + File.separator + 
        		uploadFiles.getFolderPath() + File.separator + uploadFiles.getUuid() + "_" + uploadFiles.getFileName());
        String encodedUploadFileName = UriUtils.encode(uploadFiles.getFileName(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }


//    @PostMapping("/uploadFormAction")
//    public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
//
//        String uploadFolder = "C:\\upload";
//
//        for (MultipartFile multipartFile : uploadFile) {
//
//            File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
//
//            try {
//                multipartFile.transferTo(saveFile);
//            } catch (Exception e) {
//
//            } // end catch
//        } // end for
//
//    }

//    @PostMapping("/uploadFormAction")
//    public String upload(@RequestParam MultipartFile[] uploadFile, Model model)
//            throws Exception {
//        List<UploadFiles> list = new ArrayList<>();
//        for (MultipartFile file : uploadFile) {
//            if (!file.isEmpty()) {
//                UploadFiles uploadFiles = new UploadFiles(file.getOriginalFilename(), UUID.randomUUID().toString() + "-" + file.getOriginalFilename() , file.getContentType());
//                list.add(uploadFiles);
//
//                File newFileName = new File(uploadFiles.getFullName());
//
//
//                file.transferTo(newFileName);
//                uploadService.insertUpload(uploadFiles);
//            }
//        }
//
//        model.addAttribute("files", list);
//
//
//        return "result";
//
//    }

//    @GetMapping("/loadImage")
//    public void displayImage(@RequestParam(value = "fullName") String fullName, HttpServletResponse response) throws Exception {
//        response.setContentType("image/jpg");
//        ServletOutputStream bout = response.getOutputStream();
//
//        String imgpath = "C:\\Temp\\upload"+File.separator+fullName;
//        System.out.println(imgpath);
//        FileInputStream f = new FileInputStream(imgpath);
//        int length;
//        byte[] buffer = new byte[10];
//        while ((length=f.read(buffer)) != -1){
//            bout.write(buffer,0,length);
//        }
//
//    }

    
}
