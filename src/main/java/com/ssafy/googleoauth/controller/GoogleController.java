package com.ssafy.googleoauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.gooleoauth.GoogleLoginResponse;
import com.ssafy.gooleoauth.GoogleOAuthRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URI;

@Slf4j
@RestController
public class GoogleController {

    @Value("${google.auth.url}")
    private String googleAuthUrl;

    @Value("${google.login.url}")
    private String googleLoginUrl;

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.redirect.url}")
    private String googleRedirectUrl;

    @Value("${google.secret}")
    private String googleClientSecret;
    
    

    // 구글 로그인창 호출
    // http://localhost:8080/login/getGoogleAuthUrl
    @GetMapping(value = "/login/getGoogleAuthUrl")
    public ResponseEntity<?> getGoogleAuthUrl(HttpServletRequest request) throws Exception {
    	
        String reqUrl = googleLoginUrl + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri=" + googleRedirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

        log.info("myLog-LoginUrl : {}",googleLoginUrl);
        log.info("myLog-ClientId : {}",googleClientId);
        log.info("myLog-RedirectUrl : {}",googleRedirectUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));

        //1.reqUrl 구글로그인 창을 띄우고, 로그인 후 /login/oauth_google_check 으로 리다이렉션하게 한다.
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    // 구글에서 리다이렉션
    @GetMapping(value = "/login/oauth_google_check")
    public String oauth_google_check(HttpServletRequest request,
                                     @RequestParam(value = "code") String authCode,
                                     HttpServletResponse response) throws Exception {

        // Google OAuth 토큰 요청 데이터 구성
        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest.builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .code(authCode)
                .redirectUri(googleRedirectUrl)
                .grantType("authorization_code")
                .build();

        RestTemplate restTemplate = new RestTemplate();

        // Google /token 엔드포인트 호출
        ResponseEntity<GoogleLoginResponse> apiResponse = restTemplate.postForEntity(
                googleAuthUrl + "/token",
                googleOAuthRequest,
                GoogleLoginResponse.class
        );
        GoogleLoginResponse googleLoginResponse = apiResponse.getBody();

        log.info("Token Response: {}", googleLoginResponse.toString());

        String googleToken = googleLoginResponse.getId_token();

        // Google /tokeninfo 엔드포인트 호출
        String requestUrl = UriComponentsBuilder
                .fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
                .queryParam("id_token", googleToken)
                .toUriString();

        try {
            String resultJson = restTemplate.getForObject(requestUrl, String.class);
            log.info("Token Info Response: {}", resultJson);
            return resultJson;
        } catch (Exception e) {
            log.error("Error while calling Token Info API: {}", e.getMessage());
            throw e;
        }
    }

}

