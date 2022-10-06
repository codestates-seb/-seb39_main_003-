package com.web.MyPetForApp.mail.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PwdMaker {
    private SecureRandom random = new SecureRandom();
    private static final String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String ENGLSIH_UPPER = ENGLISH_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String SPECIAL = "!@#$%^&*";
    private static final String PWD_DATA = ENGLISH_LOWER + ENGLSIH_UPPER + NUMBER + SPECIAL;

    public String createPassword(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 8; i++){
            sb.append(PWD_DATA.charAt(random.nextInt(PWD_DATA.length())));
        }

        return sb.toString();
    }
}
