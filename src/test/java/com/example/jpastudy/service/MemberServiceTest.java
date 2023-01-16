package com.example.jpastudy.service;


import com.example.jpastudy.domain.Member;
import com.example.jpastudy.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
//위에 2가지를 가지고 Spring 으로 test 가능
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    //@Autowired EntityManager em;

    @Test
    //@Rollback(false)
    //Test 할때 @Transactional 로 인해 자동으로 Rollback 되어서 DB 에 insert 들어가지 않으므로 Rollback(false) 해줘야 들어간다 .
    //Test 할땐 DB 에 데이터가 남으면 안 되기 때문에 Rollback 이 되어야 하므로 위에 코드를 사용하지 않는다 .
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setName("kim");

        //when
       Long saveId = memberService.join(member);

        //then
        //em.flush();
        //EntityManager 를 통해 Query 문으로 Insert 되는것은 확인을 하고 @Transactional 로 인해 DB 는 Rollback 된다 .
        //영속성 컨텍스트에 의한 변경 내용을 DB 에 반영한다 .
        Assert.assertEquals(member , memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); //예외가 발생해야 한다!!!
        } catch (IllegalStateException e) {
            System.out.println("test실패");
            return;
        }

        //then
        Assert.fail("예외가 발생해야 한다.");
    }
}