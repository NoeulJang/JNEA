package com.jnep.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jnep.backend.domain.User;

@Repository

public interface LoginRepository extends JpaRepository<User, Long>{
    User findByUserId(String userId);
}
