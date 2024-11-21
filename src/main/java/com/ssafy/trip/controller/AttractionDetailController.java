package com.ssafy.trip.controller;


import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/attraction")
public class AttractionDetailController {

    @Value("${tour.api.key}") // application.properties에 설정된 API 키
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/detail/{contentId}")
    public ResponseEntity<?> getAttractionDetail(@PathVariable String contentId) {
        String apiUrl = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MobileApp")
                .queryParam("_type", "json")
                .queryParam("contentId", contentId)
                .queryParam("defaultYN", "Y")
                .queryParam("firstImageYN", "Y")
                .queryParam("areacodeYN", "Y")
                .queryParam("catcodeYN", "Y")
                .queryParam("addrinfoYN", "Y")
                .queryParam("mapinfoYN", "Y")
                .queryParam("overviewYN", "Y")
                .queryParam("numOfRows", "50")
                .queryParam("serviceKey", apiKey);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);
            log.info(uriBuilder.toUriString());
           
            log.info(response.getBody());
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("API 요청 실패: " + e.getMessage());
        }
    }
}
