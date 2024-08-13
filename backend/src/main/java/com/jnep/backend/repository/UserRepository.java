package com.jnep.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jnep.backend.domain.User;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    //@PersistenceContext   -> 스프링 부트에서는 Autowierd로 변경 가능
    //private EntityManager em;

    //public UserRepository(EntityManager em){
    //  this.em = em;
    //}
    
    //spring boot에서 지원해주는 방식으로 아래처럼 수정할 수 있음
    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select m from User as m", User.class).getResultList();
    }

    public List<User> findById(String id) {
        return em.createQuery("select m from User as m where m.userId = :id", User.class).setParameter("id", id).getResultList();
    }
    public List<User> findByName(String name) {
        return em.createQuery("select m from User as m where m.userNickName = :name", User.class).setParameter("name", name).getResultList();
    }

}
