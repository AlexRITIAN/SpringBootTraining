package com.lenovo.getfiles.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GetLogFIles
 */
public class GetLogFIles {

    private Logger logger = LoggerFactory.getLogger(GetLogFIles.class);

    private String activeLogPath;
    private String startDate;
    private String endDate;
    private String storePath;

    public GetLogFIles(String activeLogPath, String startDate, String endDate, String storePath) {
        this.activeLogPath = activeLogPath;
        this.startDate = startDate;
        this.endDate = endDate;
        this.storePath = storePath;
    }

    private void getFiles(String path) throws IOException, ParseException {
        File file = new File(path);
        File[] fileArray = file.listFiles();
        // 遍历目录下的文件和子目录
        if(fileArray != null){
            for (int i = 0; i < fileArray.length; i++) {
                if (fileArray[i].isFile()) {
                    // System.out.println(fileArray[i].getPath());
                    copyFile(fileArray[i]);
                } else {
                    getFiles(fileArray[i].getPath());
                }
            }
        }
    }

    private void copyFile(File socurFile) throws IOException, ParseException {
        String targetFilePath = socurFile.getPath().replace(activeLogPath, storePath);
        File targetFile = new File(targetFilePath);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long startDateL = 0;
        long endDateL = 0;
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        logger.debug("文件路径 : " + socurFile.getPath());
        logger.debug("文件名称 : " + socurFile.getName());
        logger.debug("文件修改时间 : " + socurFile.lastModified());
        logger.debug("开始时间 : " + format.parse(startDate).getTime());
        if(startDate != null && endDate != null && !"".equals(startDate) && !"".equals(endDate)){
           startDateL = format.parse(startDate).getTime();
           endDateL = format.parse(endDate).getTime(); 
           if(socurFile.lastModified() >= startDateL && socurFile.lastModified() <= endDateL ){
               Files.copy(socurFile.toPath(), targetFile.toPath());
               System.out.println(socurFile.toPath());
           }
        }else if(startDate == null && endDate != null && "".equals(startDate) && !"".equals(endDate)){
            endDateL = format.parse(endDate).getTime();
            if(socurFile.lastModified() <= endDateL ){
                Files.copy(socurFile.toPath(), targetFile.toPath());
                System.out.println(socurFile.toPath());
            } 
        }else if(startDate != null && endDate == null && !"".equals(startDate) && "".equals(endDate)){
            startDateL = format.parse(startDate).getTime();
            if(socurFile.lastModified() >= startDateL ){
                Files.copy(socurFile.toPath(), targetFile.toPath());
                System.out.println(socurFile.toPath());
            } 
        }else{
            Files.copy(socurFile.toPath(), targetFile.toPath());
            System.out.println(socurFile.toPath());
        }
    }

    public void run() throws IOException, ParseException {
        if(activeLogPath != null){
            getFiles(activeLogPath);
        }
    }
}