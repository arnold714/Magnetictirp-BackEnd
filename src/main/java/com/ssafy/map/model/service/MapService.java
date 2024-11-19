package com.ssafy.map.model.service;


import com.ssafy.map.model.SidoCodeDto;
import com.ssafy.map.model.ThemeDto;

import java.util.List;

public interface MapService {

	List<SidoCodeDto> getSido() throws Exception;
	List<ThemeDto> getTheme() throws Exception;
}
