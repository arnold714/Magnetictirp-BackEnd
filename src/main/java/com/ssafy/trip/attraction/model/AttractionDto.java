package com.ssafy.trip.attraction.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "관광지 정보를 나타내는 DTO")
public class AttractionDto {

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

    @Schema(description = "우편번호", example = "100876")
    private String zipcode;

    @Schema(description = "전화번호", example = "02-3456-7890")
    private String tel;

    @Schema(description = "첫 번째 이미지 URL", example = "http://example.com/image1.jpg")
    private String firstImage;

    @Schema(description = "두 번째 이미지 URL", example = "http://example.com/image2.jpg")
    private String firstImage2;

    @Schema(description = "조회 수", example = "1500")
    private int readCount;

    @Schema(description = "시/도 코드", example = "1")
    private int sidoCode;

    @Schema(description = "구/군 코드", example = "101")
    private int gugunCode;

    @Schema(description = "위도", example = "37.5511694")
    private double latitude;

    @Schema(description = "경도", example = "126.9882266")
    private double longitude;

    @Schema(description = "관광지 레벨", example = "2")
    private String mlevel;

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getFirstImage2() {
        return firstImage2;
    }

    public void setFirstImage2(String firstImage2) {
        this.firstImage2 = firstImage2;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(int sidoCode) {
        this.sidoCode = sidoCode;
    }

    public int getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(int gugunCode) {
        this.gugunCode = gugunCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMlevel() {
        return mlevel;
    }

    public void setMlevel(String mlevel) {
        this.mlevel = mlevel;
    }
}
