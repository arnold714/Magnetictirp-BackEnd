package com.ssafy.trip.attraction.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "관광지 설명 정보를 나타내는 DTO")
public class AttractionDescriptionDto {

    @Schema(description = "관광지 홈페이지 URL", example = "http://example.com")
    private String homepage;

    @Schema(description = "관광지 개요", example = "이 관광지는 아름다운 경치와 다양한 활동으로 유명합니다.")
    private String overview;

    @Schema(description = "전화번호 이름", example = "대표 전화")
    private String telName;

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTelName() {
        return telName;
    }

    public void setTelName(String telName) {
        this.telName = telName;
    }
}
