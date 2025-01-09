package com.ssafy.plan.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "여행 계획을 나타내는 Response DTO")
public class PlanResponseDto {
    @Schema(description = "계획 ID", example = "12345")
    private int planId;
    @Schema(description = "제목", example = "ssafy친구들과 대만여행")
    private String title;
    @Schema(description = "이미지", example = "http://tong.visitkorea.or.kr/cms/resource/43/2766743_image2_1.jpg")
    private String image;
    @Schema(description = "공개여부", example = "1")
    private int isPublic;
    @Schema(description = "여행 일수", example = "3")
    private int tripDay;
    @Schema(description = "시/도 코드", example = "1")
    private int areaCode;
    @Schema(description = "구/군 코드", example = "23")
    private int siGunGuCode;    
    
    @Schema(description = "생성일", example = "2023-10-01T12:34:56")
    private LocalDateTime createdAt;

    public PlanResponseDto() {
        this.createdAt = LocalDateTime.now();
    }
}
