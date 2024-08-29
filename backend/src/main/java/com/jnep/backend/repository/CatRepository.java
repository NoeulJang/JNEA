package com.jnep.backend.repository;

import com.jnep.backend.repository.custom.CatCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jnep.backend.domain.Cat;

public interface CatRepository extends JpaRepository<Cat, Long>, CatCustomRepository {
}
