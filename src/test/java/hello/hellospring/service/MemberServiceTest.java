package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void atferEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(),findMember.getName());


    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다.");
//        }


        //then



    }


    @Test
    void findMembers() {


    }

    @Test
    void findOne() {


    }




}