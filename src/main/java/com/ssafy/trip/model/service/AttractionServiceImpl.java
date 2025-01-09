package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ssafy.trip.model.AttractionListDto;
import com.ssafy.trip.model.AttractionRequestDto;
import com.ssafy.trip.model.AttractionResponseDto;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.mapper.AttractionMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{
	
	private final AttractionMapper attractionMapper;

	@Override
	public AttractionListDto searchList(Map<String, String> params) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("query", params.get("query"));
		param.put("category", params.get("category"));
		param.put("sidoCode", params.get("sidoCode"));
		param.put("gugunCode", params.get("gugunCode"));
		int currentPage = Integer.parseInt(params.get("page") == null ? "1" : params.get("page"));
		int sizePerPage = Integer.parseInt(params.get("size") == null ? "20" : params.get("size"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("size", sizePerPage);
		List<AttractionResponseDto> list = attractionMapper.searchList(param);

		List<AttractionRequestDto> attractionRequestDtos = list.stream().map(attraction -> {
			AttractionRequestDto dto = new AttractionRequestDto();
			dto.setContentId(attraction.getContentId());
			dto.setContentTypeId(attraction.getContentTypeId());
			dto.setTitle(attraction.getTitle());
			dto.setAddr1(attraction.getAddr1());
			dto.setAddr2(attraction.getAddr2());
			dto.setFirstImage1(attraction.getFirstImage1());
			dto.setFirstImage2(attraction.getFirstImage2());
			dto.setAreaCode(attraction.getAreaCode());
			dto.setSiGunGuCode(attraction.getSiGunGuCode());
			dto.setLatitude(attraction.getLatitude());
			dto.setLongitude(attraction.getLongitude());
			try {
				dto.setSidoName(attractionMapper.getSidoName(attraction.getAreaCode()));
				dto.setGugunName(attractionMapper.getGugunName(attraction.getAreaCode(), attraction.getSiGunGuCode()));
				dto.setContentTypeName(attractionMapper.getContentTypeName(attraction.getContentTypeId()));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return dto;
		}).collect(Collectors.toList());

		AttractionListDto attractionListDto = new AttractionListDto();
		attractionListDto.setAttractions(attractionRequestDtos);
		return attractionListDto;
	}

	@Override
	public AttractionListDto favoriteList() throws Exception {
		List<AttractionResponseDto> list = attractionMapper.favoriteList();
		log.info("favoriteList list - {}", list);
		List<AttractionRequestDto> attractionRequestDtos = list.stream().map(attraction -> {
			AttractionRequestDto dto = new AttractionRequestDto();
			dto.setContentId(attraction.getContentId());
			dto.setContentTypeId(attraction.getContentTypeId());
			dto.setTitle(attraction.getTitle());
			dto.setAddr1(attraction.getAddr1());
			dto.setAddr2(attraction.getAddr2());
			dto.setFirstImage1(attraction.getFirstImage1());
			dto.setFirstImage2(attraction.getFirstImage2());
			dto.setAreaCode(attraction.getAreaCode());
			dto.setSiGunGuCode(attraction.getSiGunGuCode());
			dto.setLatitude(attraction.getLatitude());
			dto.setLongitude(attraction.getLongitude());
			try {
				dto.setSidoName(attractionMapper.getSidoName(attraction.getAreaCode()));
				dto.setGugunName(attractionMapper.getGugunName(attraction.getAreaCode(), attraction.getSiGunGuCode()));
				dto.setContentTypeName(attractionMapper.getContentTypeName(attraction.getContentTypeId()));

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return dto;
		}).collect(Collectors.toList());
		AttractionListDto attractionListDto = new AttractionListDto();
		attractionListDto.setAttractions(attractionRequestDtos);
		return attractionListDto;
	}

	@Override
	public AttractionListDto listAttraction(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		param.put("area", map.get("area") == null ? "" : map.get("area"));
		param.put("theme", map.get("theme") == null ? "" : map.get("theme"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("size", sizePerPage);
		log.info("listAttraction param - {}", param);

		List<AttractionResponseDto> list = attractionMapper.listAttraction(param);
		log.info("listAttraction list - {}", list);

		List<AttractionRequestDto> attractionRequestDtos = list.stream().map(attraction -> {
			AttractionRequestDto dto = new AttractionRequestDto();
			dto.setContentId(attraction.getContentId());
			dto.setContentTypeId(attraction.getContentTypeId());
			dto.setTitle(attraction.getTitle());
			dto.setAddr1(attraction.getAddr1());
			dto.setAddr2(attraction.getAddr2());
			dto.setFirstImage1(attraction.getFirstImage1());
			dto.setFirstImage2(attraction.getFirstImage2());
			dto.setAreaCode(attraction.getAreaCode());
			dto.setSiGunGuCode(attraction.getSiGunGuCode());
			dto.setLatitude(attraction.getLatitude());
			dto.setLongitude(attraction.getLongitude());
            try {
                dto.setSidoName(attractionMapper.getSidoName(attraction.getAreaCode()));
				dto.setGugunName(attractionMapper.getGugunName(attraction.getAreaCode(), attraction.getSiGunGuCode()));
				dto.setContentTypeName(attractionMapper.getContentTypeName(attraction.getContentTypeId()));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
			return dto;
		}).collect(Collectors.toList());

		int totalArticleCount = attractionMapper.getTotalAttractionCount(param);
		log.info("listAttraction totalArticleCount - {}", totalArticleCount);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		AttractionListDto attractionListDto = new AttractionListDto();
		attractionListDto.setAttractions(attractionRequestDtos);
		attractionListDto.setCurrentPage(currentPage);
		attractionListDto.setTotalPageCount(totalPageCount);
		return attractionListDto;
	}

	@Override
	public String getContentTypeId(int contentId) throws Exception {
		// TODO Auto-generated method stub
		return attractionMapper.getContentTypeId(contentId);
	}
}
