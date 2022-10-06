package com.web.MyPetForApp.util;

import org.springframework.stereotype.Component;

@Component
public class InfoMasking {
    public String doMask(String info) {
        // 첫글자만 제외하고 모두 *로 치환
        if(info.length() > 1) {
            String pre = info.substring(0, 1);
            String tail = info.substring(1);
            tail = tail.replaceAll(".*", "*");
            return pre + tail;
        } else {
            return info;
        }
    }
}
