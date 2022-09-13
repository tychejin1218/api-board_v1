package com.api.board.task;

import com.api.board.domain.UploadFiles;
import com.api.board.service.UploadService;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
public class FileCheckTask {

    @Autowired
    private UploadService uploadService;

    @Value("${upload.path}")
    private String uploadPath;

    private String getFolderYesterday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String str = sdf.format(cal.getTime());
        log.info("str : " + str);
        return str.replace("-", File.separator);
    }

//    @Scheduled(cron = "0,10,20,30,40,50 * * * * *")
    @Scheduled(cron = "0 0 2 * * *")
    public void checkFiles() throws Exception {
        log.warn("File Check Task run .....");
        log.warn(new Date());
        // file list in database
        List<UploadFiles> fileList = uploadService.getOldFiles();

        // ready for check file in directory with database file list
        List<Path> fileListPaths = fileList.stream()
                .map(uploadFiles-> Paths.get(uploadPath, uploadFiles.getFolderPath(),
                        "/"+uploadFiles.getUuid()+"_"+uploadFiles.getFileName()))
                .collect(Collectors.toList());

        // image file has thumbnail file
        fileList.stream().map(uploadFiles -> Paths.get(uploadPath, uploadFiles.getFolderPath(),
                        "/s_"+uploadFiles.getUuid()+"_"+uploadFiles.getFileName()))
                .forEach(p-> fileListPaths.add(p));

        log.warn("==============================================");
        fileListPaths.forEach(p -> log.warn(p));

        // files in yesterday directory
        File targetDir = Paths.get(uploadPath, getFolderYesterday()).toFile();
        log.info("targetDir: " + targetDir);
        File[] removeFiles =
                targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
        log.warn("------------------------------------------------");
        for (File file : removeFiles) {
//            log.warn(file.getAbsolutePath());
            file.delete();
        }

    }
}
