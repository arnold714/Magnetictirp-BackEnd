package com.ssafy.notices.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.notice.model.FileInfoDto;
import com.ssafy.notice.model.NoticesDto;


@Mapper
public interface NoticesMapper {

	void writeArticle(NoticesDto boardDto) throws SQLException;

	void registerFile(NoticesDto boardDto) throws Exception;

	List<NoticesDto> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	NoticesDto getArticle(int articleNo) throws SQLException;

	//void updateHit(int articleNo) throws SQLException;

	void modifyArticle(NoticesDto boardDto) throws SQLException;

	void deleteFile(int articleNo) throws Exception;

	void deleteArticle(int articleNo) throws SQLException;

	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
	
}
