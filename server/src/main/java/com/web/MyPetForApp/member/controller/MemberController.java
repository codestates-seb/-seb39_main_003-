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
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    private final MemberMapper mapper;

    // 테스트용
    @GetMapping
    public String test() {
        return "드디어 메인 프로젝트가 시작되었습니다!";
    }

    @PostMapping
    public ResponseEntity join(@RequestBody MemberDto.Post post) {
        Member savedMember = memberService.create(mapper.memberPostDtoToMember(post));
        return new ResponseEntity<>(mapper.memberToResponse(savedMember), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity memberModify(@RequestBody MemberDto.Patch patch) {
        Member modifiedMember = memberService.update(mapper.memberPatchToMember(patch));
        return new ResponseEntity<>(mapper.memberToResponse(modifiedMember), HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity memberFind(@PathVariable Long memberId) {
        Member findMember = memberService.read(memberId);
        return new ResponseEntity<>(mapper.memberToResponse(findMember) ,HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity memberDelete(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
