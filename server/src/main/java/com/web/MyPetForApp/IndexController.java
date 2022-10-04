package com.web.MyPetForApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @GetMapping("/login/oauth2/code/google")
//    public String loginAfter() {
//        return "/oauth2/authorization/google";
//    }
}
