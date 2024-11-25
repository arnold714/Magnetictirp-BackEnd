package com.ssafy.comment.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.comment.model.CommentDto;
import com.ssafy.comment.model.mapper.CommentMapper;
@Service
public class CommentServiceImpl implements CommentService{

	CommentMapper commentMapper;
	public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
	@Override
	public void writeComment(CommentDto commentDto) throws Exception {
		// TODO Auto-generated method stub
		commentMapper.writeComment(commentDto);
	}



//	@Override
//	public CommentDto getComment(int articleNo) throws Exception {
//		// TODO Auto-generated method stub
//		return commentMapper.getComment(articleNo);
//	}

	@Override
	public void modifyComment(CommentDto commentDto) throws Exception {
		// TODO Auto-generated method stub
		commentMapper.modifyComment(commentDto);
	}

	@Override
	public void deleteComment(Map<String, String> param) throws Exception {
		// TODO Auto-generated method stub
		commentMapper.deleteComment(param);
	}



	@Override
	public List<CommentDto> getListComment(int commentId) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.getListComment(commentId);
	}

}
