package com.lenovo.deletefiles.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * GetLogFIles
 */
public class GetLogFIles {

    private String activeLogPath;
    private String endDate;

    public GetLogFIles(String activeLogPath, String endDate) {
        this.activeLogPath = activeLogPath;
        this.endDate = endDate;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long endDateL = 0;
        if(endDate != null && !"".equals(endDate)){
           endDateL = format.parse(endDate).getTime(); 
           if(socurFile.lastModified() <= endDateL ){
               socurFile.delete();
               System.out.println(socurFile.toPath() + " has been Delete!!!");
           }
        }
    }

    public void run() throws IOException, ParseException {
        if(activeLogPath != null){
            getFiles(activeLogPath);
        }
    }
}