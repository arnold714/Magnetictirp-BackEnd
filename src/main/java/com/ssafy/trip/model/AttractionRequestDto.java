package com.ssafy.trip.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "관광지 정보를 나타내는 Request DTO")
public class AttractionRequestDto {

    @Schema(description = "콘텐츠 ID", example = "12345")
    private int contentId;

    @Schema(description = "콘텐츠 타입 ID", example = "12")
    private int contentTypeId;

    @Schema(description = "관광지 제목", example = "서울타워")
    private String title;

    @Schema(description = "주소 1", example = "서울특별시 중구 남산공원길 105")
    private String addr1;

    @Schema(description = "주소 2", example = "남산서울타워")
    private String addr2;

    @Schema(description = "첫 번째 이미지 URL", example = "http://example.com/image1.jpg")
    private String firstImage1;

    @Schema(description = "두 번째 이미지 URL", example = "http://example.com/image2.jpg")
    private String firstImage2;

    @Schema(description = "시/도 코드", example = "1")
    private int areaCode;

    @Schema(description = "구/군 코드", example = "23")
    private int siGunGuCode;

    @Schema(description = "위도", example = "37.5511694")
    private double latitude;

    @Schema(description = "경도", example = "126.9882266")
    private double longitude;

    @Schema(description = "시/도 이름", example = "서울특별시")
    private String sidoName;

    @Schema(description = "구/군 이름", example = "중구")
    private String gugunName;

    @Schema(description = "콘텐츠 타입 이름", example = "관광지")
    private String contentTypeName;
}