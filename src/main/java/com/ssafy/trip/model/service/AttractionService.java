package com.ssafy.trip.model.service;

import com.ssafy.trip.model.AttractionDetailDto;
import com.ssafy.trip.model.AttractionListDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AttractionService {
	AttractionListDto listAttraction(Map<String, String> map) throws Exception;
	// 콘텐츠 타입Id 가져오기 
	String getContentTypeId(int contentId)throws Exception;
	AttractionListDto searchList(Map<String, String> params) throws Exception;
}
