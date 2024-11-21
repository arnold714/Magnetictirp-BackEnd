package com.ssafy.trip.controller;


import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.trip.model.service.AttractionService;
import com.ssafy.trip.model.service.AttractionServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attraction")
public class AttractionDetailController {
	private final AttractionService attractiondetail ;
    @Value("${tour.api.key}") // application.properties에 설정된 API 키
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/detail/{contentId}")
    public ResponseEntity<?> getAttractionDetail(@PathVariable String contentId) {
        String detailApiUrl = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";
        String detailIntroApiUrl = "https://apis.data.go.kr/B551011/KorService1/detailIntro1?";
        String detailImageApiUrl = "https://apis.data.go.kr/B551011/KorService1/detailImage1?";
        String encodedApiKey=null;
        String contentTypeId=null;
        System.out.println(contentTypeId);
        try {
			contentTypeId = attractiondetail.getContentTypeId(Integer.parseInt(contentId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			encodedApiKey = URLEncoder.encode(apiKey, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         String detailUriString = new StringBuilder()
        	    .append("https://apis.data.go.kr/B551011/KorService1/detailCommon1?")
        	    .append("MobileOS=ETC")
        	    .append("&MobileApp=MobileApp")
        	    .append("&_type=json")
        	    .append("&contentId=")
        	    .append(contentId)
        	    .append("&defaultYN=Y")
        	    .append("&firstImageYN=Y")
        	    .append("&areacodeYN=Y")
        	    .append("&catcodeYN=Y")
        	    .append("&addrinfoYN=Y")
        	    .append("&mapinfoYN=Y")
        	    .append("&overviewYN=Y")
        	    .append("&numOfRows=50")
        	    .append("&serviceKey=")
        	    .append(encodedApiKey)
        	    .toString();
         String detailIntroUriString = new StringBuilder()
         	    .append(detailIntroApiUrl)
         	    .append("MobileOS=ETC")
         	    .append("&MobileApp=MobileApp")
         	    .append("&_type=json")
         	    .append("&contentId=")
         	    .append(contentId)
         	    .append("&contentTypeId=")
         	    .append(contentTypeId)
         	    .append("&numOfRows=50")
         	    .append("&pageNo=1")
         	    .append("&serviceKey=")
         	    .append(encodedApiKey)
         	    .toString();
         String detailImgUriString = new StringBuilder()
          	    .append(detailImageApiUrl)
          	    .append("MobileOS=ETC")
          	    .append("&MobileApp=MobileApp")
          	    .append("&_type=json")
          	    .append("&contentId=")
          	    .append(contentId)
          	    .append("&imageYN=Y")
          	    .append("&subImageYN=Y")
          	    .append("&numOfRows=10")
          	    .append("&pageNo=1")
          	    .append("&serviceKey=")
          	    .append(encodedApiKey)
          	    .toString();
         
        URI detailCommonUrl = URI.create(detailUriString);
        URI detailIntroUrl  = URI.create(detailIntroUriString);
        URI detailImgUrl = URI.create(detailImgUriString);
        log.info("Request URL: {}", detailCommonUrl);
        log.info("Request URL: {}", detailIntroUrl);
        log.info("Request URL: {}", detailImgUrl);

        try {
        	
        CompletableFuture<ResponseEntity<String>> detailCommonFuture = CompletableFuture.supplyAsync(() ->
             restTemplate.getForEntity(detailCommonUrl, String.class)
         );
         CompletableFuture<ResponseEntity<String>> detailIntroFuture = CompletableFuture.supplyAsync(() ->
             restTemplate.getForEntity(detailIntroUrl, String.class)
         );
         CompletableFuture<ResponseEntity<String>> detailImgFuture = CompletableFuture.supplyAsync(() ->
             restTemplate.getForEntity(detailImgUrl, String.class)
         );

         // 모든 비동기 호출 완료 대기
         CompletableFuture.allOf(detailCommonFuture, detailIntroFuture, detailImgFuture).join();

         // 결과 병합
         Map<String, Object> combinedResponse = new HashMap<>();
         combinedResponse.put("detailCommon", detailCommonFuture.get().getBody());
         combinedResponse.put("detailIntro", detailIntroFuture.get().getBody());
         combinedResponse.put("detailImg", detailImgFuture.get().getBody());
         log.info(combinedResponse.toString());
         return ResponseEntity.ok(combinedResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("API 요청 실패: " + e.getMessage());
        }
    }
}
