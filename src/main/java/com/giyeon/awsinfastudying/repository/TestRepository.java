package com.giyeon.awsinfastudying.repository;

import com.giyeon.awsinfastudying.domain.Test;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final EntityManager em;

    public void save(Test test) {
        em.persist(test);
    }

    public Test getImage() {
        return em.createQuery("select t from Test t where t.id = :id", Test.class)
                .setParameter("id", 1L)
                .getSingleResult();
    }
}


//    return em.createQuery(
//            "SELECT t FROM Test t WHERE t.id = :id", Test.class)
//        .setParameter("id", 1L)
//        .getSingleResult();