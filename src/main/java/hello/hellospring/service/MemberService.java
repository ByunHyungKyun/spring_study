package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        //같은 이름이 있는 중복 회원은 안된다.


        //  이 로직이 아래 로직 처럼 바꿀수 있다.
        //Optional<Member> result = memberRepository.findByname(member.getName());
        //result.ifPresent(m -> {
        //    throw new IllegalStateException("이미 존재하는 회원입니다.");
        //});

        validateDuplicationMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        memberRepository.findByname(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers(){
       return  memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

}











