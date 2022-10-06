//package com.web.MyPetForApp.mail.util;
//
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDateTime;
//
//@Component
//public class MessageCreator {
//
//    public String createMessageAboutFindPwd(String newPwd) {
//
//        String text = "";
//        text += "<div style='margin:100px;'>";
//        text += "<h1> 안녕하세요! <h1>";
//        text += "<h1> 반려 동물 종합 사이트 위풍댕댕 입니다.</h1>";
//        text += "<br>";
//        text += "<p> 회원님의 임시 비밀번호를 안내해 드립니다.";
//        text += "<div align='center' style='border:1px solid black; font-family:verdana;'>";
//        text += "<div style='font-size:130%'>";
//        text += "<strong>NEW PASSWORD : " + newPwd + "</strong>";
//        text += "</div></div>";
//        text += "<br>";
//        text += "<p> 로그인 후 반드시 비밀번호를 변경해 주세요 !!!";
//
//        return text;
//    }
//
//    public String createMessageAboutErrorOccured(String message){
//
//        String text = "";
//        text += "<p> 발생시간 : " + LocalDateTime.now();
//        text += "<br>";
//        text += "<div style='border:1px solid black; font-family:verdana;'>";
//        text += "<div style='font-size:130%'>";
//        text += "<p><strong>에러 메시지</strong>;";
//        text += "<br>";
//        text += message;
//        text += "</div></div>";
//
//        return text;
//    }
//}
