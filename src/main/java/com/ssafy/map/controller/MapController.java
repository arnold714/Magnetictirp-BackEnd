package com.ssafy.map.controller;

import com.ssafy.map.model.SidoCodeDto;
import com.ssafy.map.model.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/map")
@Tag(name = "시도 컨트롤러", description = "시도 정보를 처리하는 클래스.")
@Slf4j
public class MapController {
	
	private final MapService mapService;

	public MapController(MapService mapService) {
		super();
		this.mapService = mapService;
	}

	@Operation(summary = "시도 정보", description = "전국의 시도를 반환한다.")
	@GetMapping("/sido")
	public ResponseEntity<List<SidoCodeDto>> sido() throws Exception {
		log.info("sido - 호출");
		return new ResponseEntity<List<SidoCodeDto>>(mapService.getSido(), HttpStatus.OK);
	}

	
}
