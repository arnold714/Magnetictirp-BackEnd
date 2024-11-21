package com.ssafy.plan.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "여행 계획를 나타내는 Request DTO")
public class PlanRequestDto {
    @Schema(description = "계획 ID", example = "12345")
    private int planId;
    @Schema(description = "제목", example = "ssafy 친구들과 대만여행")
    private String title;
    @Schema(description = "공개여부", example = "1")
    private int isPublic;
    @Schema(description = "여행 일수", example = "2박3일")
    private String tripDay;
    @Schema(description = "시/도 코드", example = "1")
    private int areaCode;
    @Schema(description = "구/군 코드", example = "23")
    private int siGunGuCode;
    @Schema(description = "생성일", example = "23-10-01")
    private String createdAt;
    @Schema(description = "시/도 이름", example = "서울특별시")
    private String sidoName;
    @Schema(description = "구/군 이름", example = "중구")
    private String gugunName;
}
