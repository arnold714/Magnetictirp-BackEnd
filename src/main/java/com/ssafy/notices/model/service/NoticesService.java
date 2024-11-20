package com.ssafy.notices.model.service;

import java.util.Map;

import com.ssafy.notice.model.NoticesDto;
import com.ssafy.notice.model.NoticesListDto;


public interface NoticesService {

	void writeArticle(NoticesDto noticesDto) throws Exception;
	NoticesListDto listArticle(Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	NoticesDto getArticle(int noticesNo) throws Exception;
	//void updateHit(int noticesNo) throws Exception;
	
	void modifyArticle(NoticesDto noticesDto) throws Exception;
//	
	void deleteArticle(int noticesNo) throws Exception;
	
}
