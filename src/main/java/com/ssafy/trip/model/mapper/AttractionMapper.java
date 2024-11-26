package com.ssafy.trip.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.plan.model.PlanResponseDto;
import com.ssafy.trip.model.AttractionDetailDto;
import com.ssafy.trip.model.AttractionResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.MultiValueMap;

@Mapper
public interface AttractionMapper {
	List<AttractionResponseDto> listAttraction(Map<String, Object> map) throws SQLException;
	int getTotalAttractionCount(Map<String, Object> param) throws SQLException;
	String getSidoName(@Param("sidoCode") int sidoCode) throws SQLException;
	String getGugunName(@Param("sidoCode") int sidoCode, @Param("gugunCode") int gugunCode) throws SQLException;
	String getContentTypeName(@Param("contentTypeId") int contentTypeId) throws SQLException;
	String getContentTypeId(int contentId)throws SQLException;
	List<AttractionResponseDto> searchList(Map<String, Object> param) throws SQLException;
	List<AttractionResponseDto> favoriteList() throws SQLException;
}
