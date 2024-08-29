package com.jnep.backend.repository.custom;

import com.jnep.backend.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatCustomRepository{
    List<Cat> findByCatName(String catName);
    Cat findByCatId(Long catId);
}
