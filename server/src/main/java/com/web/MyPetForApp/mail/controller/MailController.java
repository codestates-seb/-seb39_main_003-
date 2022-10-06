//package com.web.MyPetForApp.mail.controller;
//
//import com.web.MyPetForApp.mail.service.MailService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@Tag(name = "메일링 API")
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/v1/mail")
//public class MailController {
//    private final MailService mailService;
//
//    @Operation(summary = "비밀번호 찾기", description = "이메일을 보내주면 해당 이메일로 새 비밀번호가 랜덤으로 전송되고" +
//            "그 비밀번호로 유저가 로그인하여 다시 변경하는 방식으로 비밀번호 찾기 구현")
//    @ApiResponses(
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "OK"
//            )
//    )
//    @PostMapping("/findPwd")
//    public ResponseEntity mailTest(@Parameter(description = "유저 이메일") @RequestParam String toAddress) {
//
//        mailService.newPasswordMail(toAddress);
//
//        return new ResponseEntity<>("mail send success", HttpStatus.OK);
//    }
//}
