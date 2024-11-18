package com.ssafy.trip.attraction.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "지역 코드 정보를 나타내는 DTO")
public class AreaCodeDto {

    @Schema(description = "시/도 코드", example = "1")
    private int sidoCode;

    @Schema(description = "시/도 이름", example = "서울특별시")
    private String sidoName;

    @Schema(description = "구/군 코드", example = "101")
    private int gugunCode;

    @Schema(description = "구/군 이름", example = "강남구")
    private String gugunName;

    public int getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(int sidoCode) {
        this.sidoCode = sidoCode;
    }

    public String getSidoName() {
        return sidoName;
    }

    public void setSidoName(String sidoName) {
        this.sidoName = sidoName;
    }

    public int getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(int gugunCode) {
        this.gugunCode = gugunCode;
    }

    public String getGugunName() {
        return gugunName;
    }

    public void setGugunName(String gugunName) {
        this.gugunName = gugunName;
    }
}
