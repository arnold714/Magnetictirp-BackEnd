package com.ssafy.trip.model.service;

import com.ssafy.trip.model.AttractionDetailDto;
import com.ssafy.trip.model.AttractionListDto;

import java.util.List;
import java.util.Map;

public interface AttractionService {
	AttractionListDto listAttraction(Map<String, String> map) throws Exception;
}
