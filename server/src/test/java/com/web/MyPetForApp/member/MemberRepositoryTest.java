//package com.web.MyPetForApp.member;
//
//import com.web.MyPetForApp.member.entity.Member;
//import com.web.MyPetForApp.member.repository.MemberRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
////@DataJpaTest
////@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class MemberRepositoryTest {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    private Member member;
//
//    private Member savedMember;
//
//    // save() 테스트 겸, 다른 테스트들을 위한 데이터 초기화
//    @BeforeEach
//    public void init() {
//        // given
//        member = Member.builder()
//                .memberName("abc6")
//                .nickName("별명6")
//                .phone("010-6666-6666")
//                .address("주소6")
//                .email("abc6@abc.com")
//                .password("6666")
//                .build();
//        // when
//        savedMember = memberRepository.save(member);
//        // then
//        assertNotNull(savedMember);
//        assertTrue(savedMember.getMemberName().equals(member.getMemberName()));
//        assertTrue(savedMember.getNickName().equals(member.getNickName()));
//        assertTrue(savedMember.getEmail().equals(member.getEmail()));
//        assertTrue(savedMember.getPassword().equals(member.getPassword()));
//    }
//
//    @Test
//    public void find() {
//        // when
//        Member findMember = memberRepository.findByEmail(savedMember.getEmail()).get();
//        // then
//        assertNotNull(findMember);
//        assertTrue(findMember.getPassword().equals(savedMember.getPassword()));
//        assertTrue(findMember.getMemberId() == savedMember.getMemberId());
//    }
//
//    @Test
//    public void update() {
//        // when
//        Member findMember = memberRepository.findByEmail(savedMember.getEmail()).get();
//        Member updateMember = Member.builder()
//                            .nickName("변한놈2")
//                            .password("3333")
//                            .build();
//        findMember.updateMember(updateMember);
//        memberRepository.save(findMember);
//
//        System.out.println(findMember.getPassword());
//        System.out.println(updateMember.getPassword());
//
//        // then
//        assertTrue(findMember.getPassword().equals(updateMember.getPassword()));
//        assertTrue(findMember.getNickName().equals(updateMember.getNickName()));
//    }
//
//    @Test
//    public void delete() {
//        // when
//        memberRepository.delete(savedMember);
//        Optional<Member> optionalMember = memberRepository.findByEmail(savedMember.getEmail());
//        // then
//        assertTrue(optionalMember.isEmpty());
//    }
//
////    @Test
////    public void findLastMemberIdTest() {
////        // when
////        Long maxId = memberRepository.findLastMemberId();
////        // then
////        assertTrue(maxId == 6L);
////        System.out.println("maxId = " + maxId);
////    }
//}
