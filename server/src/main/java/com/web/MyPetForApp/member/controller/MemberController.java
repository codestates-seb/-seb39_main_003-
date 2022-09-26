package com.web.MyPetForApp.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.member.dto.MemberDto;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.mapper.MemberMapper;
import com.web.MyPetForApp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private final ImageService imageService;
    private final MemberMapper mapper;

    // 테스트용
    @GetMapping("/user/test")
    public String test() {
        return "일반유저 권한 테스트";
    }

    @GetMapping("/admin/test")
    public String test2() {
        return "관리자 모드 테스트";
    }

    @PostMapping("/member")
    public ResponseEntity join(@RequestBody MemberDto.Post post,
                                @RequestPart(required = false) List<MultipartFile> multipartFiles) {
//        MemberDto.Post post = null;
//        try {
//            post = new ObjectMapper().readValue(jsonBody, MemberDto.Post.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        String memberId = memberService.createMemberId();

        Member savedMember = memberService.create(mapper.memberPostDtoToMember(post, memberId));

        String profileImg = null;

        if(multipartFiles != null) {
            profileImg  = imageService.uploadFile(multipartFiles, "member", memberId).get(0);
        }
        return new ResponseEntity<>(mapper.memberToResponse(savedMember, profileImg), HttpStatus.CREATED);
    }

    @PatchMapping("/member")
    public ResponseEntity memberModify(@RequestBody MemberDto.Patch patch,
                                       @RequestPart(required = false) List<MultipartFile> multipartFiles) {
        Member modifiedMember = memberService.update(mapper.memberPatchToMember(patch));
        String profileImg = null;

        if(multipartFiles != null) {
            profileImg  = imageService.uploadFile(multipartFiles, "member", patch.getMemberId()).get(0);
        }
        return new ResponseEntity<>(mapper.memberToResponse(modifiedMember, profileImg), HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity memberFind(@PathVariable String memberId) {
        Member findMember = memberService.read(memberId);
        String profileImg = imageService.findFilesById("member" ,memberId).get(0);

        return new ResponseEntity<>(mapper.memberToResponse(findMember, profileImg) ,HttpStatus.OK);
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity memberDelete(@PathVariable String memberId) {
        memberService.delete(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/member/findEmail")
    public ResponseEntity findEmail(@RequestParam String phone) {
//        1. 전화번호가 Request로 들어온다.
//        2. 전화번호로 인증 문자/메일을 보낸다. (인증 과정 생략)
//        3. 사용자가 인증버튼을 누른다.
//        4. 인증이 성공하면 전화번호를 통해 회원의 Email을 조회하여 응답데이터로 보낸다.
        String findEmail = memberService.emailFind(phone);
        return new ResponseEntity<>(findEmail ,HttpStatus.OK);
    }
    // 비밀번호 찾기
    @GetMapping("/member/findPassword")
    public ResponseEntity findPasswordReRequest(@RequestParam String email, @RequestParam String phone) {
//        1. 사용자 이메일 / 전화번호가 요청으로 들어온다.
//        2. 이메일 / 전화번호가 맞는지 확인 후, 새로운 비밀번호를 달라고 재 요청을 달라고한다. (맞지 않으면 검증 실패 응답 메시지 반환)
        memberService.emailValidate(email);
        memberService.phoneValidate(phone);
        return new ResponseEntity<>("need new password", HttpStatus.OK);
    }

    @PatchMapping("/member/findPassword")
    public ResponseEntity findPasswordChange(@RequestParam String email, @RequestParam String password) {
//        3. 새 비밀번호가 요청으로 오면, 기존 비밀번호와 일치하는지 확인 후, 일치하지 않으면 그 비밀번호로 저장한다.
//        4. 일치한다면 기존 비밀번호와 다른 비밀번호를 요청해달라고 요청한다.
        memberService.passwordChance(email, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
