//package com.web.MyPetForApp.auth.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import static org.hibernate.internal.CoreLogging.logger;
//
//@RestController
//@RequestMapping("auth")
//public class AuthController {
//    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
//
//    @GetMapping("/refresh")
//    public ResponseEntity refreshNeed() {
//        logger("refresh Token을 요청합니다.");
//        return new ResponseEntity<>("refresh token need", HttpStatus.UNAUTHORIZED);
//    }
//}
