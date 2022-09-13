package com.web.MyPetForApp.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    // 테스트용
    @GetMapping
    public String test() {
        return "드디어 메인 프로젝트가 시작되었습니다!";
    }
}
