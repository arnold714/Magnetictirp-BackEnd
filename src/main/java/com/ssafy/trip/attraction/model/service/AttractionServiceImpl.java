package com.ssafy.trip.attraction.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ssafy.trip.attraction.model.AreaCodeDto;
import com.ssafy.trip.attraction.model.AttractionDescriptionDto;
import com.ssafy.trip.attraction.model.mapper.AttractionMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{
	
	private final AttractionMapper attractionMapper;
	
	@Override
	public List<AreaCodeDto> getAreaCode(int areaCode) throws Exception {
		// TODO Auto-generated method stub
		return attractionMapper.getGugun(areaCode);
	}

	@Override
	public List<AttractionDescriptionDto> listAttraction(MultiValueMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return attractionMapper.listAttraction(map);
	}

	@Override
	public AttractionDescriptionDto detailAttraction(int contentId) throws Exception {
		// TODO Auto-generated method stub
		return attractionMapper.detailAttraction(contentId);
	}
	
}
