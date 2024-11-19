package com.ssafy.gooleoauth;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.member.controller.MemberController;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@Tag(name = "GoogleOAuth Controller", description = "GoogleOAuth로 계정 이동")
public class GoogleOAuthController {

	 private final MemberService memberServiceoauth;
	    private final JavaMailSender mailSenderoauth;

	    public GoogleOAuthController(MemberService memberServiceoauth, JavaMailSender mailSenderoauth) {
	        this.memberServiceoauth = memberServiceoauth;
	        this.mailSenderoauth = mailSenderoauth;
	        
	    }
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
    @Operation(summary = "구글 oauth 이동", description = "구글 계정 확인으로 이동합니다")
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
    
    @Operation(summary = "Handle Google OAuth callback", description = "구글로 로그인 후 유저 정보 콜백")
    @GetMapping(value = "/login/oauth_google_check")
    public ResponseEntity<String> oauth_google_check(HttpServletRequest request,
                                     @RequestParam(value = "code") String authCode,
                                     HttpServletResponse response, HttpSession session) throws Exception {

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
            MemberDto resultJson = restTemplate.getForObject(requestUrl, MemberDto.class);
            log.info("Token Info Response: {}", resultJson);
            MemberDto memberDto = new MemberDto();
            
            memberDto.setSub(resultJson.getSub());
            memberDto.setEmail(resultJson.getEmail());
            memberDto.setName(resultJson.getName());
            memberDto.setPicture(resultJson.getPicture());
            
            session.setAttribute("userinfo", memberDto.toString());
            
            if(memberServiceoauth.emailCheck(resultJson.getEmail())!=1) {
            	memberServiceoauth.joinMember(memberDto);
            }
            MemberDto membersession = memberServiceoauth.loginMember(memberDto);
            
            log.info("myLog-LoginUrl : {}",membersession.toString());
            session.setAttribute("userinfo", membersession);
            
            
            return ResponseEntity.status(HttpStatus.CREATED).body("로그인 성공");
        } catch (Exception e) {
            log.error("Error while calling Token Info API: {}", e.getMessage());
            throw e;
        }
    }
}
