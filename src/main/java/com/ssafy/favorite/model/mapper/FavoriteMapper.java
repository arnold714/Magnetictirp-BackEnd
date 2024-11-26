package com.ssafy.favorite.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.favorite.model.FavoriteDto;
@Mapper
public interface FavoriteMapper {

	
	void registerFavorite(FavoriteDto fav) throws SQLException;
	void deleteFavorite(FavoriteDto fav) throws SQLException;
	List<FavoriteDto> listFavorite(String email) throws SQLException;
}
