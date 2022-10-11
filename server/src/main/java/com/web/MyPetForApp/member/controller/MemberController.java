package com.web.MyPetForApp.member.controller;

import com.web.MyPetForApp.member.dto.MemberDto;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.mapper.MemberMapper;
import com.web.MyPetForApp.member.service.MemberService;
import com.web.MyPetForApp.util.StringIdGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Tag(name = "회원 API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;
    private final StringIdGenerator stringIdGenerator;

    // 테스트용
    @GetMapping("/user/test")
    public String test() {
        return "일반유저 권한 테스트";
    }

    @GetMapping("/admin/test")
    public String test2() {
        return "관리자 모드 테스트";
    }

    @Operation(summary = "회원 가입")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED"
            )
    )
    @PostMapping(value = "/member", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity join(@Valid @ParameterObject @ModelAttribute MemberDto.Post post,
                                @RequestPart(required = false) List<MultipartFile> multipartFiles) throws Exception {


        String memberId = stringIdGenerator.createMemberId();

        Member savedMember = memberService.create(mapper.memberPostDtoToMember(post, memberId), multipartFiles);

        return new ResponseEntity<>(mapper.memberToResponse(savedMember), HttpStatus.CREATED);
    }

    @Operation(summary = "회원 정보 수정")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping(value = "/member", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity memberModify(@Valid @ParameterObject @ModelAttribute MemberDto.Patch patch,
                                       @RequestPart(required = false) List<MultipartFile> multipartFiles) throws Exception {

        Member modifiedMember = memberService.update(mapper.memberPatchToMember(patch), multipartFiles);
        return new ResponseEntity<>(mapper.memberToResponse(modifiedMember), HttpStatus.OK);
    }

    @Operation(summary = "하나의 회원 정보 조회")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/member/{memberId}")
    public ResponseEntity memberFind(@NotBlank @PathVariable String memberId) throws Exception {
        Member findMember = memberService.read(memberId);

        return new ResponseEntity<>(mapper.memberToResponse(findMember) ,HttpStatus.OK);
    }

    @Operation(summary = "회원탈퇴")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    )
    @DeleteMapping("/member/{memberId}")
    public ResponseEntity memberDelete(@NotBlank @PathVariable String memberId) {
        memberService.delete(memberId);
        return new ResponseEntity<>("delete Ok" ,HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "회원 이메일 찾기")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/member/findEmail")
    public ResponseEntity findEmail(@Parameter(description = "인증에 쓰일 휴대폰번호") @RequestParam String phone) {
//        1. 전화번호가 Request로 들어온다.
//        2. 전화번호로 인증 문자/메일을 보낸다. (인증 과정 생략)
//        3. 사용자가 인증버튼을 누른다.
//        4. 인증이 성공하면 전화번호를 통해 회원의 Email을 조회하여 응답데이터로 보낸다.
        String findEmail = memberService.emailFind(phone);
        return new ResponseEntity<>(findEmail ,HttpStatus.OK);
    }
    // 비밀번호 찾기
    @Operation(summary = "회원 비밀번호 찾기(1) : 유저 검증")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @GetMapping("/member/findPassword")
    public ResponseEntity findPasswordReRequest(@Parameter(description = "인증에 쓰일 이메일") @RequestParam String email,
                                                @Parameter(description = "인증에 쓰일 휴대폰번호") @RequestParam String phone) {
//        1. 사용자 이메일 / 전화번호가 요청으로 들어온다.
//        2. 이메일 / 전화번호가 맞는지 확인 후, 새로운 비밀번호를 달라고 재 요청을 달라고한다. (맞지 않으면 검증 실패 응답 메시지 반환)
        memberService.emailValidate(email);
        memberService.phoneValidate(phone);
        return new ResponseEntity<>("need new password", HttpStatus.OK);
    }
    @Operation(summary = "회원 비밀번호 찾기(2) : 새 비밀번호로 변경")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            )
    )
    @PatchMapping("/member/findPassword")
    public ResponseEntity findPasswordChange(@Parameter(description = "인증에 쓰일 이메일") @RequestParam String email,
                                             @Parameter(description = "새 비밀번호") @RequestParam String password) {
//        3. 새 비밀번호가 요청으로 오면, 기존 비밀번호와 일치하는지 확인 후, 일치하지 않으면 그 비밀번호로 저장한다.
//        4. 일치한다면 기존 비밀번호와 다른 비밀번호를 요청해달라고 요청한다.
        memberService.passwordChance(email, password);
        return new ResponseEntity<>("success" ,HttpStatus.OK);
    }

}
