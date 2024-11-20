package com.ssafy.notices.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.notice.model.FileInfoDto;
import com.ssafy.notice.model.NoticesDto;
import com.ssafy.notice.model.NoticesListDto;
import com.ssafy.notices.model.mapper.NoticesMapper;



@Service
public class NoticesServiceImpl implements NoticesService {

	private final NoticesMapper noticesMapper;

	public NoticesServiceImpl(NoticesMapper noticesMapper) {
		super();
		this.noticesMapper = noticesMapper;
	}

	@Override
	@Transactional
	public void writeArticle(NoticesDto noticesDto) throws Exception {
		noticesMapper.writeArticle(noticesDto);
		List<FileInfoDto> fileInfos = noticesDto.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			noticesMapper.registerFile(noticesDto);
		}
	}

	@Override
	public NoticesListDto listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);

		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		if ("user_id".equals(key))
			param.put("key", key == null ? "" : "b.user_id");
		List<NoticesDto> list = noticesMapper.listArticle(param);

		if ("user_id".equals(key))
			param.put("key", key == null ? "" : "user_id");
		int totalArticleCount = noticesMapper.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		NoticesListDto noticesListDto = new NoticesListDto();
		noticesListDto.setArticles(list);
		noticesListDto.setCurrentPage(currentPage);
		noticesListDto.setTotalPageCount(totalPageCount);

		return noticesListDto;
	}

//	@Override
//	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
//		PageNavigation pageNavigation = new PageNavigation();
//
//		int naviSize = SizeConstant.NAVIGATION_SIZE;
//		int sizePerPage = SizeConstant.LIST_SIZE;
//		int currentPage = Integer.parseInt(map.get("pgno"));
//
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int totalCount = boardMapper.getTotalArticleCount(param);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//
//		return pageNavigation;
//	}

	@Override
	public NoticesDto getArticle(int noticesNo) throws Exception {
		return noticesMapper.getArticle(noticesNo);
	}

	//@Override
	//public void updateHit(int noticesNo) throws Exception {
	//	noticesMapper.updateHit(noticesNo);
	//}

	@Override
	public void modifyArticle(NoticesDto noticesDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		noticesMapper.modifyArticle(noticesDto);
	}

//	@Override
//	@Transactional
//	public void deleteArticle(int articleNo, String path) throws Exception {
//		// TODO : BoardDaoImpl의 deleteArticle 호출
//		List<FileInfoDto> fileList = boardMapper.fileInfoList(articleNo);
//		boardMapper.deleteFile(articleNo);
//		boardMapper.deleteArticle(articleNo);
//		for(FileInfoDto fileInfoDto : fileList) {
//			File file = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
//			file.delete();
//		}
//	}

	@Override
	public void deleteArticle(int noticesNo) throws Exception {
		// TODO : BoardDaoImpl의 deleteArticle 호출
		noticesMapper.deleteArticle(noticesNo);
	}

}
