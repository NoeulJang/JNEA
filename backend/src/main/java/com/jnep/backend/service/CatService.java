package com.jnep.backend.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jnep.backend.domain.Cat;
import com.jnep.backend.repository.CatRepository;

import lombok.RequiredArgsConstructor;


@Service
//Spring Transcational 사용 권장
//readOnly true 를 사용하여 조회 시 더티체킹 x, 영속성 컨텍스트를 플러시 하지 않는다, DB에 따라서 리소스를 최적화 시키는 등의 역할
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatService {

    //final 사용 권장
    private final CatRepository catRepository;

    /*
     * 고양이 등록
    */
    @Transactional
    public Long catSave(Cat cat){
        Cat saveCat = catRepository.save(cat);
        return saveCat.getCatId();
    }

    /*
     * 고양이 정보 수정
    */
    @Transactional
    public void updateCat (Long catId, Cat cat){
        //준영속 엔티티의 변경감지 기능을 사용하여 수정
        //임의로 만들어낸 엔티티도 기존 식별자를 가지고 있으면 준영속 엔티티로 간주하여 jpa가 수정작업을 진행함
        Cat findCat = catRepository.findByCatId(catId);
        findCat.setCatName(null);
    }

    /*
     * 고양이 정보 단건 조회
    */
    @Transactional
    public Cat findByCatId (Long catId){
        return catRepository.findByCatId(catId);
    }

    /*
     * 고양이 정보 목록 조회
     */
    public List<Cat> search(String keyword) {
        return catRepository.findByCatName(keyword);
    }


}
