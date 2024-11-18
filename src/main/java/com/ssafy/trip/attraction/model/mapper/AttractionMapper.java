package com.ssafy.trip.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.MultiValueMap;

import com.ssafy.trip.attraction.model.AreaCodeDto;
import com.ssafy.trip.attraction.model.AttractionDescriptionDto;

@Mapper
public interface AttractionMapper {
	List<AreaCodeDto> getSido() throws SQLException;
	
	List<AreaCodeDto> getGugun(int areaCode) throws SQLException;

	List<AttractionDescriptionDto> listAttraction(MultiValueMap<String, String> map) throws SQLException;

	AttractionDescriptionDto detailAttraction(int contentId) throws SQLException;
		
	
}
