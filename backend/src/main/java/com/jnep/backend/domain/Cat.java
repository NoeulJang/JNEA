package com.jnep.backend.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CAT_INFO")
//무한루프를 방지하기 위해 해당 테이블은 toString 에서 제외
@ToString(exclude = {"user"})
public class Cat {

    @Id
    @Column(name = "CAT_ID")
    @GeneratedValue
    private Long catId;

    @Column(name = "CAT_NAME", length = 20)
    private String catName;

    @Column(name = "CAT_AGE", length = 3)
    private Long catAge;

    @Column(name = "CAT_WEIGHT", length = 3)
    private Long catWeight;

    @Column(name = "CAT_SEX", length = 1)
    private String catSex;

    @Column(name = "CAT_SPCI", length = 200)
    private String catSpci;

    @Column(name = "CAT_DIS", length = 10000)
    private String catDis;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "SAVE_FILE_NAME")
    private String saveFileName;

    @Column(name = "FILE_EXT")
    private String fileExt;

    @Column(name = "FILE_COURS")
    private String fileCours;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @Column(name = "REG_DT")
    private LocalDateTime regDt;


    //연관관계의 주체로 값을 넣으면 FK값이 변경된다.
    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;

    //AllArgsConstructor 대신 Builder 적용하여 아래사항 방지
    /*
        AllArgsConstructor는 클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성하는데,
        인스턴스 멤버의 선언 순서에 영향을 받기 때문에 변수의 순서를 바꾸면 생성자의 입력 값 순서도 바뀌게
        되어 검출되지 않는 치명적인 오류를 발생시킬 수 있음
    */
    @Builder
    public Cat(String catName, Long catAge, Long catWeight, String catSex, String catSpci, String catDis, String fileName, String saveFileName,
            String fileExt, String fileCours, Long fileSize) {
        this.catName = catName;
        this.catAge = catAge;
        this.catWeight = catWeight;
        this.catSex = catSex;
        this.catSpci = catSpci;
        this.catDis = catDis;
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.fileExt = fileExt;
        this.fileCours = fileCours;
        this.fileSize = fileSize;
    }
}
