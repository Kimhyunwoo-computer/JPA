package com.example.jpastudy.service;

import com.example.jpastudy.domain.Member;
import com.example.jpastudy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
//DB 에서 읽기만 하는 경우 (readOnly = true) 를 써주면서 부하를 줄여준다 .
@RequiredArgsConstructor
public class MemberService {

    //@Autowired
    //injection 주입을 field 에 직접 해주면 test case 에 어려움이 있어서 생성자에 injection 주입을 해준다 .

    //생성자를 직접 작성하지 않고 Lombok 을 이용해서 @RequireArgsConstructor 을 이용해서 final field 에 대해 생성자를 만들고 injection 을 주입한다 .
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    //DB 에서 쓰기를 해야하는 경우에 (readonly = true) 를 사용하면 안 써지므로 따로 Annotation 선언해줘야한다 .
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = new Member();
        member.setName(name);

    }
}
