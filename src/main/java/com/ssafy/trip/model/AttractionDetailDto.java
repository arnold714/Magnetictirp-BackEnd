package com.ssafy.trip.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "관광지 상세 정보를 나타내는 DTO")
public class AttractionDetailDto {

    @Schema(description = "카테고리 1", example = "A01")
    private String cat1;

    @Schema(description = "카테고리 2", example = "A0101")
    private String cat2;

    @Schema(description = "카테고리 3", example = "A01010100")
    private String cat3;

    @Schema(description = "생성 시간", example = "2022-01-01T10:00:00")
    private String createdTime;

    @Schema(description = "수정 시간", example = "2022-02-01T12:00:00")
    private String modifiedTime;

    @Schema(description = "예약 가능 여부", example = "Y")
    private String bookTour;

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public void setCat3(String cat3) {
        this.cat3 = cat3;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getBookTour() {
        return bookTour;
    }

    public void setBookTour(String bookTour) {
        this.bookTour = bookTour;
    }
}
