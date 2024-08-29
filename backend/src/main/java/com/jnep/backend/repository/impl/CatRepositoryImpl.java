package com.jnep.backend.repository.impl;

import com.jnep.backend.repository.custom.CatCustomRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import com.jnep.backend.domain.Cat;
import com.jnep.backend.repository.CatRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.jnep.backend.domain.QCat.cat;

@Repository
@AllArgsConstructor
public class CatRepositoryImpl implements CatCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Cat> findByCatName(String catName) {
        return jpaQueryFactory.selectFrom(cat).where(cat.catName.contains(catName)).fetch();
    }

    @Override
    public Cat findByCatId(Long catId) {
        return jpaQueryFactory.selectFrom(cat).where(cat.catId.eq(catId)).fetchOne();
    }
}
