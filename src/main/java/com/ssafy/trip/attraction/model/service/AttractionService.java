package com.ssafy.trip.attraction.model.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.ssafy.trip.attraction.model.AreaCodeDto;
import com.ssafy.trip.attraction.model.AttractionDescriptionDto;

public interface AttractionService {
	List<AreaCodeDto> getAreaCode(int areaCode) throws Exception;
	List<AttractionDescriptionDto> listAttraction(MultiValueMap<String, String> map) throws Exception;
	AttractionDescriptionDto detailAttraction(int contentId) throws Exception;
}
