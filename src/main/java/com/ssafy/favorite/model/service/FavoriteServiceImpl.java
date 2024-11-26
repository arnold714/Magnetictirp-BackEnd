package com.ssafy.favorite.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.comment.model.CommentDto;
import com.ssafy.comment.model.mapper.CommentMapper;
import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.favorite.model.mapper.FavoriteMapper;
@Service
public class FavoriteServiceImpl implements FavoriteService {

	FavoriteMapper favoriteMapper;

	public FavoriteServiceImpl(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }
	
	@Override
	public void registerFavorite(FavoriteDto fav) throws Exception {
		favoriteMapper.registerFavorite(fav);
		favoriteMapper.incrementFavoriteHit(fav.getContentId());
		
	}

	@Override
	public void deleteFavorite(FavoriteDto fav) throws Exception {
		// TODO Auto-generated method stub
		 favoriteMapper.decrementFavoriteHit(fav.getContentId());
		 favoriteMapper.deleteFavorite(fav);
	}

	@Override
	public List<FavoriteDto> listFavorite(String email) throws Exception {
		return favoriteMapper.listFavorite(email);
		
	}

	
}
