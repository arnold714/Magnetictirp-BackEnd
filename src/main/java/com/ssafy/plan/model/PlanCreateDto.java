package com.ssafy.plan.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(title = "PlanCreateDto : 여행 계획 생성", description = "여행 계획 생성 정보를 나타낸다.")
public class PlanCreateDto {
    @Schema(description = "여행 계획 ID", example = "1")
    private int planId;
    @Schema(description = "여행 기간", example = "2")
    private int duration;
    @Schema(description = "시/도 코드", example = "1")
    private int areaCode;
    @Schema(description = "구/군 코드", example = "23")
    private Integer sigunguCode;
    @Schema(description = "여행 계획 제목", example = "서울 2박3일 여행")
    private String title;
}
