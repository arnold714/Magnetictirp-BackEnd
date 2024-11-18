package com.ssafy.map.model.service;

import com.ssafy.map.model.SidoCodeDto;
import com.ssafy.map.model.mapper.MapMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {
	
	private final MapMapper mapMapper;

	public MapServiceImpl(MapMapper mapMapper) {
		super();
		this.mapMapper = mapMapper;
	}

	@Override
	public List<SidoCodeDto> getSido() throws Exception {
		return mapMapper.getSido();
	}


}
