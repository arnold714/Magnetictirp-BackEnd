package com.ssafy.comment.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.comment.model.CommentDto;

public interface CommentService {

	

	void writeComment(CommentDto commentDto) throws Exception;

	List<CommentDto> getListComment(int commentId) throws Exception;
	
//	CommentDto getComment(int articleNo) throws Exception;

	//void updateHit(int articleNo) throws SQLException;

	void modifyComment(CommentDto commentDto) throws Exception;

	
	void deleteComment(Map<String,String> param) throws Exception;

}
