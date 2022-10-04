package com.web.MyPetForApp.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping(value = "/oauth/google/login")
    public ResponseEntity googleLogin() {

        String authBase_url = "https://accounts.google.com/o/oauth2/v2/auth?";
        String client_id = "62650287293-9d9l1m28qe92h0ek0d0ul6ghovkf834s.apps.googleusercontent.com";
        String redirect_url = "http://localhost:8080/login/oauth2/code/google";
        String response_type = "token";
        String response_type2 = "code";
        String scope = "https://www.googleapis.com/auth/userinfo.email";

        String direct = authBase_url +
                "client_id=" + client_id +
                "&response_type=" + response_type +
                "&redirect_uri=http://localhost:8080/login/oauth2/code/google" +
                "&scope=https://www.googleapis.com/auth/userinfo.email";

        String direct_ec2 = authBase_url +
                "client_id=" + client_id +
                "&response_type=" + response_type +
                "&redirect_uri=http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/login/oauth2/code/google" +
                "&scope=https://www.googleapis.com/auth/userinfo.email";

        String local_default = "http://localhost:8080/oauth2/authorization/google";

        String ip_default = "http://211.204.66.186:8080/oauth2/authorization/google";

        String ec2_domain_default = "http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/login/oauth2/code/google";

        String direct2 = authBase_url +
                "response_type=" + response_type2 +
                "&client_id=" + client_id +
                "&scope=" + scope +
                "&redirect_uri=" + redirect_url;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity<>(headers);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.set("grant_type", GRANT_TYPE);
//        parameters.add("response_type","code");
//        parameters.add("client_id","62650287293-9d9l1m28qe92h0ek0d0ul6ghovkf834s.apps.googleusercontent.com");
//        parameters.add("scope","profile email");
//        parameters.add("redirect_uri", "http://211.204.66.186:8080/login/oauth2/code/google");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers, parameters);

        RestTemplate template = new RestTemplate();

        return template.postForEntity(local_default,request,String.class);
//        ResponseEntity<Map> response =  template.postForEntity(url2 ,request ,Map.class);
//        ResponseEntity response = template.getForEntity(url, String.class);
//        System.out.println(response.getStatusCode());
//        String auth = response.getHeaders().getFirst("Authorization");
//        System.out.println("auth = " + auth);
    }

    @GetMapping("/login/oauth2/code/google")
    public String  takeRedirect() {
        return "리다이렉트 되었다";
    }
}
