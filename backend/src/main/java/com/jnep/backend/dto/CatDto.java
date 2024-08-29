package com.jnep.backend.dto;

import org.springframework.web.multipart.MultipartFile;

import com.jnep.backend.domain.Cat;
import com.jnep.backend.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@ToString
public class CatDto {

    public CatDto(Long catId, String catName, Long catAge, String catDis, String catSpci, Long catWeight, String catSex,
            MultipartFile file) {
        this.catId = catId;
        this.catName = catName;
        this.catAge = catAge;
        this.catDis = catDis;
        this.catSpci = catSpci;
        this.catWeight = catWeight;
        this.catSex = catSex;
        this.file = file;
    }
    
    
    public CatDto(Long catId, String catName, Long catAge, String catDis, String catSpci, Long catWeight, String catSex,
    UploadFile uploadFile) {
        this.catId = catId;
        this.catName = catName;
        this.catAge = catAge;
        this.catDis = catDis;
        this.catSpci = catSpci;
        this.catWeight = catWeight;
        this.catSex = catSex;
        this.uploadFile = uploadFile;
    }

    private Long catId;

    private String catName;

    private Long catAge;

    //고양이병명
    private String catDis;

    //고양이 종류
    private String catSpci;

    //고양이 체중
    private Long catWeight;

    private String catSex;

    // private String fileName;

    // private String saveFileName;

    // private String fileExt;

    // private String fileCours;

    // private Long fileSize;

    private MultipartFile file;

    private UploadFile uploadFile;

    public Cat toEntity(User user, UploadFile pUploadFile){
        Cat cat = Cat.builder()
                    .catName(catName)
                    .catAge(catAge)
                    .catDis(catDis)
                    .catSpci(catSpci)
                    .catWeight(catWeight)
                    .catSex(catSex)
                    .fileName(pUploadFile.getUploadFileName())
                    .saveFileName(pUploadFile.getSaveFileName())
                    .fileExt(pUploadFile.getFileExt())
                    .fileCours(pUploadFile.getFileCours())
                    .fileSize(pUploadFile.getFileSize())
                    .build();

        return cat;
    };
}
