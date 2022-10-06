package com.web.MyPetForApp.mail.controller;

import com.web.MyPetForApp.mail.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/findPwd")
    public ResponseEntity mailTest(@RequestParam String toAddress) {

        mailService.newPasswordMail(toAddress);

        return new ResponseEntity<>("mail send success", HttpStatus.OK);
    }
}
