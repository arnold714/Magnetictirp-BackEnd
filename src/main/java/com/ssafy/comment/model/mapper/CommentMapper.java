package com.ssafy.comment.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.comment.model.CommentDto;


@Mapper
public interface CommentMapper {

	void writeComment(CommentDto commentDto) throws SQLException;

	List<CommentDto> getListComment(int commentId) throws SQLException;

	
//	CommentDto getComment(int articleNo) throws SQLException;

	//void updateHit(int articleNo) throws SQLException;

	void modifyComment(CommentDto commentDto) throws SQLException;

	
	void deleteComment(Map<String,String> param) throws SQLException;

	List<CommentDto> userComment(String email)throws SQLException;
	
}
