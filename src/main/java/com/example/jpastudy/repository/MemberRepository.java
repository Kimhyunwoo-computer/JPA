package com.example.jpastudy.repository;

import com.example.jpastudy.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext
    //EntityManager 는 @Autowired 로 injection 이 안되고 @PersistenceContext 로 injection 을 한다 .

    //Service 처럼 injection 주입을 생성자 주입으로 하면서 @RequiredArgsConstructor 로 final field 에 대해서 injection 을 한다 .
    private final EntityManager em;

    //Member Entity 를 넣으면 DB 에 반영이 된다 . insert query 가 나간다 .
    public void save(Member member) {
        em.persist(member);
    }

    //findOne 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m" , Member.class)
                .getResultList();
        //Query 문 만들때 SQL 문은 from 의 대상이 table 에 대해서 select 를 하지만 JPQL 에 대해서는 Entity 에 class 에 대해서 select 하는 모습
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name" , Member.class)
                .setParameter("name" , name)
                .getResultList();
    }
}
