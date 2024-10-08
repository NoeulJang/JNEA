package com.jnep.backend.Util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jnep.backend.dto.UploadFile;

@Component
public class FileUploadUtil {
    
    @Value("${file.dir}")
    private String fileDir;

    /** 전체 파일 경로 */
    public String getFullPath(String fileName){
        return fileDir + fileName;
    }

    /** 파일 저장 **/
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException{
        if(multipartFile.isEmpty()) return null;

        String originalFileName = multipartFile.getOriginalFilename();

        /** 서버에 저장할 새로운 파일명 생성 **/
        String storeFileName = createStoreFileName(originalFileName);

        /** 새 파일명으로 파일 저장 **/
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        Long fileSize = multipartFile.getSize();

        String extension = extractExtension(originalFileName);

        return new UploadFile(originalFileName, storeFileName, fileSize, extension, fileDir);
    }


    /** 확장자명 추출 메서드 **/
    private String extractExtension(String originalFileName){
        int position = originalFileName.lastIndexOf("."); // 확장자명 위치
        String extension = "." + originalFileName.substring(position + 1); // 확장자명 추출

        return extension;
    }

    /** 서버에 저장할 파일명 생성 **/
    private String createStoreFileName(String originalFileName){
        // 서버에 저장하는 파일명
        return UUID.randomUUID().toString();
    }
}
