package com.ssafy.map.model.mapper;

import com.ssafy.map.model.SidoCodeDto;
import com.ssafy.map.model.ThemeDto;

import java.sql.SQLException;
import java.util.List;

public interface MapMapper {

	List<SidoCodeDto> getSido() throws SQLException;
	List<ThemeDto> getTheme() throws SQLException;
}
