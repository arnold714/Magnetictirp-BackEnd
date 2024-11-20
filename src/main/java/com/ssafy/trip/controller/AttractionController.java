package com.ssafy.trip.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.model.AttractionDetailDto;
import com.ssafy.trip.model.AttractionListDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.service.AttractionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
@Tag(name = "Attraction Controller", description = "관광지 관련 API")
public class AttractionController {

    private final AttractionService attractionService;

    @Operation(summary = "관광지 목록 조회", description = "쿼리 매개변수를 사용하여 관광지 목록을 필터링하여 조회합니다.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "게시글목록 OK!!"),
            @ApiResponse(responseCode = "404", description = "페이지없어!!"),
            @ApiResponse(responseCode = "500", description = "서버에러!!") })
    @GetMapping("/list")
    public ResponseEntity<?> listAttraction(
            @Parameter(description = "검색 필터 매개변수", required = true)
            @RequestParam Map<String, String> map) throws Exception {
        log.info("listAttraction map - {}", map);
        try{
            AttractionListDto list = attractionService.listAttraction(map);
            log.info("listAttraction list - {}", list);
            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            return ResponseEntity.status(500).body(Map.of("message", "서버 오류 발생"));
         }
    }

}
