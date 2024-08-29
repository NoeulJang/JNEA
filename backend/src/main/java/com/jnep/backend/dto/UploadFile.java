package com.jnep.backend.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UploadFile {

    private String uploadFileName;

    private String saveFileName;

    private Long fileSize;

    private String fileExt;

    private String fileCours;

    public UploadFile(String uploadFileName, String saveFileName, Long fileSize, String fileExt, String fileCours) {
        this.uploadFileName = uploadFileName;
        this.saveFileName = saveFileName;
        this.fileSize = fileSize;
        this.fileExt = fileExt;
    }
    
}
