package com.ssafy.favorite.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.comment.controller.CommentController;
import com.ssafy.comment.model.CommentDto;
import com.ssafy.comment.model.service.CommentService;
import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.favorite.model.service.FavoriteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/favorite")
@Slf4j
public class FavoriteController {

	private final FavoriteService favoriteService;

	public FavoriteController(FavoriteService favoriteService) {
		super();
		this.favoriteService = favoriteService;
	}

	
	@GetMapping
	public ResponseEntity<?> registerFavorite(
			@RequestBody @Parameter(description = "작성글 정보.", required = true) FavoriteDto favoriteDto) {
		log.info("writeComment commentDto - {}", favoriteDto);
		try {
			favoriteService.registerFavorite(favoriteDto);

			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> deleteFavorite(
			@RequestBody @Parameter(description = "작성글 정보.", required = true) FavoriteDto favoriteDto) {
		log.info("writeComment commentDto - {}", favoriteDto);
		try {
			favoriteService.deleteFavorite(favoriteDto);

			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getListComment(
	    @PathVariable("email") @Parameter(description = "공지사항을 얻기 위한 contentId.", required = true) String email) {
	    log.info("getListComment contentId - {}", email);
	    try {
	        List<FavoriteDto> favoriteList = favoriteService.listFavorite(email);
	        for(FavoriteDto comm : favoriteList) {
	        	log.info(comm.toString());
	        }
	        HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	        return ResponseEntity.ok().headers(header).body(favoriteList);
	    } catch (Exception e) {
	        return exceptionHandling(e);
	    }
	}
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
