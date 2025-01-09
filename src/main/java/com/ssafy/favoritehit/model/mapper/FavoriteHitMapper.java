package com.ssafy.favoritehit.model.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface FavoriteHitMapper {

    // 특정 content_id의 hit 값 조회
    int getFavoriteHit(int contentId) throws SQLException;

    // 모든 favorite_hit 데이터 조회
    List<Map<String, Object>> getAllFavoriteHits()throws SQLException;

    // 특정 사용자의 favorite_hit 데이터 조회
    List<Map<String, Object>> getUserFavoriteHits(String email)throws SQLException;
    
    // 좋아요 목록 크기 순 가져오기  
    List<Map<String,Object>> getHitList() throws SQLException;
}
