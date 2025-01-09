package com.ssafy.favorite.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.comment.model.CommentDto;
import com.ssafy.favorite.model.FavoriteDto;

public interface FavoriteService {

	
	void registerFavorite(FavoriteDto fav) throws Exception;
	void deleteFavorite(FavoriteDto fav) throws Exception;
	List<FavoriteDto> listFavorite(String email)throws Exception;
	
}
