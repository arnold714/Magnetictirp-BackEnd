package com.ssafy.favoritehit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.favorite.controller.FavoriteController;
import com.ssafy.favoritehit.service.FavoriteHitService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/favoritehit")
public class FavoriteHitController {

    private final FavoriteHitService favoriteHitService;

    @Autowired
    public FavoriteHitController(FavoriteHitService favoriteHitService) {
        this.favoriteHitService = favoriteHitService;
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<?> getFavoriteHit(@PathVariable int contentId) {
    	try {
            int hit = favoriteHitService.getFavoriteHit(contentId);
            return new ResponseEntity<>(hit, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAllFavoriteHits() {
        List<Map<String, Object>> hits=null;
		try {
			hits = favoriteHitService.getAllFavoriteHits();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(hits, HttpStatus.OK);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Map<String, Object>>> getUserFavoriteHits(@PathVariable String email) {
        List<Map<String, Object>> userHits=null;
		try {
			userHits = favoriteHitService.getUserFavoriteHits(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(userHits, HttpStatus.OK);
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> getHitList() {
        List<Map<String, Object>> list=null;
		try {
			list = favoriteHitService.getHitList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
