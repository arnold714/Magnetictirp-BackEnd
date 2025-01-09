package com.ssafy.favoritehit.service;

import com.ssafy.favoritehit.model.mapper.FavoriteHitMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class FavoriteHitServiceImpl implements FavoriteHitService {

    private final FavoriteHitMapper favoriteHitMapper;

    @Autowired
    public FavoriteHitServiceImpl(FavoriteHitMapper favoriteHitMapper) {
        this.favoriteHitMapper = favoriteHitMapper;
    }

    @Override
    public int getFavoriteHit(int contentId) throws Exception  {
        return favoriteHitMapper.getFavoriteHit(contentId);
    }

    @Override
    public List<Map<String, Object>> getAllFavoriteHits() throws Exception  {
        return favoriteHitMapper.getAllFavoriteHits();
    }

    @Override
    public List<Map<String, Object>> getUserFavoriteHits(String email) throws Exception  {
        return favoriteHitMapper.getUserFavoriteHits(email);
    }

	@Override
	public List<Map<String, Object>> getHitList() throws Exception {
		// TODO Auto-generated method stub
		return favoriteHitMapper.getHitList();
	}
}