package com.ssafy.favoritehit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FavoriteHitService {

    // 특정 content_id의 hit 값 조회
    int getFavoriteHit(int contentId) throws Exception ;

    // 모든 favorite_hit 데이터 조회
    List<Map<String, Object>> getAllFavoriteHits() throws Exception ;

    // 특정 사용자의 favorite_hit 데이터 조회
    List<Map<String, Object>> getUserFavoriteHits(String email) throws Exception ;
    
    // 
    List<Map<String,Object>> getHitList() throws Exception;
}
