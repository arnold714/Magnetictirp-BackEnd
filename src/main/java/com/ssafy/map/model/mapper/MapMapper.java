package com.ssafy.map.model.mapper;

import com.ssafy.map.model.SidoCodeDto;

import java.sql.SQLException;
import java.util.List;

public interface MapMapper {

	List<SidoCodeDto> getSido() throws SQLException;
	
}
