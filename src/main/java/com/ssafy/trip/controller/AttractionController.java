package com.ssafy.trip.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.attraction.model.AreaCodeDto;
import com.ssafy.trip.attraction.model.AttractionDescriptionDto;
import com.ssafy.trip.attraction.model.service.AttractionService;

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

    @Operation(summary = "지역 코드 조회", description = "주어진 지역 코드를 통해 지역 정보를 조회합니다.")
    @GetMapping("/area/{code}")
    public ResponseEntity<?> getAreaCode(
            @Parameter(description = "조회할 지역 코드", example = "1") 
            @PathVariable("code") int areaCode) {

        try {
            List<AreaCodeDto> list = attractionService.getAreaCode(areaCode);
            if (list.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("message", "해당 지역 정보를 찾을 수 없습니다."));
            }
            Map<String, Object> response = Map.of("list", list);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "서버 오류 발생"));
        }
    }

    @Operation(summary = "관광지 목록 조회", description = "쿼리 매개변수를 사용하여 관광지 목록을 필터링하여 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<?> listAttraction(
            @Parameter(description = "검색 필터 매개변수")
            @RequestParam MultiValueMap<String, String> map) throws Exception {
        List<AttractionDescriptionDto> list = attractionService.listAttraction(map);
        return ResponseEntity.ok(Map.of("list", list));
    }

    @Operation(summary = "관광지 상세 정보 조회", description = "콘텐츠 ID를 통해 관광지의 상세 정보를 조회합니다.")
    @GetMapping("/detail/{contentId}")
    public ResponseEntity<?> detailAttraction(
            @Parameter(description = "조회할 콘텐츠 ID", example = "101") 
            @PathVariable("contentId") int contentId) {
        try {
            AttractionDescriptionDto attractionDescriptionDto = attractionService.detailAttraction(contentId);
            if (attractionDescriptionDto == null) {
                return ResponseEntity.status(404).body(Map.of("message", "해당 콘텐츠 정보를 찾을 수 없습니다."));
            }
            Map<String, Object> response = Map.of("attraction", attractionDescriptionDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "서버 오류 발생"));
        }
    }
}
