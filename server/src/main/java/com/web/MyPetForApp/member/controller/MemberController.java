package com.web.MyPetForApp.member.controller;

import com.web.MyPetForApp.member.dto.MemberDto;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.mapper.MemberMapper;
import com.web.MyPetForApp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

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
    public ResponseEntity join(@RequestBody MemberDto.Post post) {
        Member savedMember = memberService.create(mapper.memberPostDtoToMember(post));
        return new ResponseEntity<>(mapper.memberToResponse(savedMember), HttpStatus.CREATED);
    }

    @PatchMapping("/member")
    public ResponseEntity memberModify(@RequestBody MemberDto.Patch patch) {
        Member modifiedMember = memberService.update(mapper.memberPatchToMember(patch));
        return new ResponseEntity<>(mapper.memberToResponse(modifiedMember), HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity memberFind(@PathVariable Long memberId) {
        Member findMember = memberService.read(memberId);
        return new ResponseEntity<>(mapper.memberToResponse(findMember) ,HttpStatus.OK);
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity memberDelete(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
