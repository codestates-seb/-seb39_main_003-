//package com.web.MyPetForApp.mail.service;
//
//import com.web.MyPetForApp.exception.BusinessLogicException;
//import com.web.MyPetForApp.exception.ExceptionCode;
//import com.web.MyPetForApp.mail.util.MessageCreator;
//import com.web.MyPetForApp.mail.util.PwdMaker;
//import com.web.MyPetForApp.member.entity.Member;
//import com.web.MyPetForApp.member.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.mail.Message;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//
//@Service
//@RequiredArgsConstructor
//public class MailService {
//    private final JavaMailSender mailSender;
//    private final MessageCreator messageCreator;
//    private final PwdMaker pwdMaker;
//    private final MemberService memberService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private static final String FROM_ADDRESS = "wprhks536@gmail.com";
//
//    public void newPasswordMail(String toAddress) {
//        Member member = memberService.findVerifiedMember(memberService.searchMemberIdByEmail(toAddress));
//
//        String newPassword = pwdMaker.createPassword();
//
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//
//            message.setRecipients(Message.RecipientType.TO, toAddress);
//            message.setFrom(new InternetAddress(FROM_ADDRESS, "위풍댕댕", "UTF-8"));
//            message.setSubject(Title.FIND_PASSWORD.str);
//            message.setText(messageCreator.createMessageAboutFindPwd(newPassword),
//                    "utf-8",
//                    "html");
//
//            mailSender.send(message);
//        } catch (Exception e) {
//            throw new BusinessLogicException(ExceptionCode.CANNOT_SEND_EMAIL);
//        }
//
//        member.updatePassword(bCryptPasswordEncoder.encode(newPassword));
//        memberService.update(member, null);
//    }
//    public void unknownErrorMail(Exception error){
//        StringWriter sw = new StringWriter();
//        error.printStackTrace(new PrintWriter(sw));
//
//        try {
//            InternetAddress[] addresses = {
//                    new InternetAddress("aries278@naver.com"),
//                    new InternetAddress("rmsqja2@gmail.com"),
//                    new InternetAddress("deae670@daum.net")
//            };
//
//            MimeMessage message = mailSender.createMimeMessage();
//
//            message.setRecipients(Message.RecipientType.TO, addresses);
//            message.setFrom(new InternetAddress(FROM_ADDRESS, "위풍댕댕", "UTF-8"));
//            message.setSubject(Title.UNKNOWN_ERROR_OCCUR.str);
//            message.setText(messageCreator.createMessageAboutErrorOccured(sw.toString()),
//                    "utf-8",
//                    "html");
//
//            mailSender.send(message);
//        }catch (Exception e){
//            throw new BusinessLogicException(ExceptionCode.CANNOT_SEND_EMAIL);
//        }
//    }
//
//    public enum Title {
//        FIND_PASSWORD("[위풍댕댕] 새로운 비밀번호가 도착했습니다."),
//        UNKNOWN_ERROR_OCCUR("[위풍댕댕] 미확인 에러 발생!");
//
//        private final String str;
//
//        Title(String str) {
//            this.str = str;
//        }
//    }
//}
