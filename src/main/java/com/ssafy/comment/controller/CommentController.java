package com.ssafy.comment.controller;


import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.comment.model.CommentDto;
import com.ssafy.comment.model.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/comment")
@Tag(name = "댓긋 게시판 컨트롤러", description = "댓글 게시판에 글을 등록, 수정, 삭제, 목록, 상세보기등 전반적인 처리를 하는 클래스.")
@Slf4j
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@Operation(summary = "댓글 게시판 글작성", description = "")
	@PostMapping
	public ResponseEntity<?> writeComment(
			@RequestBody @Parameter(description = "작성글 정보.", required = true) CommentDto commentDto) {
		log.info("writeComment commentDto - {}", commentDto);
		try {
			commentService.writeComment(commentDto);
//			return ResponseEntity.ok().build();
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "댓글 게시판 ", description = "댓글 리스트 가져오기 ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "공지사항목록 OK!!"),
			@ApiResponse(responseCode = "404", description = "페이지없어!!"),
			@ApiResponse(responseCode = "500", description = "서버에러!!") })
	@GetMapping("/{contentId}")
	public ResponseEntity<?> getListComment(
	    @PathVariable("contentId") @Parameter(description = "공지사항을 얻기 위한 contentId.", required = true) int contentId) {
	    log.info("getListComment contentId - {}", contentId);
	    try {
	        List<CommentDto> commentList = commentService.getListComment(contentId);
	        for(CommentDto comm : commentList) {
	        	log.info(comm.toString());
	        }
	        HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	        return ResponseEntity.ok().headers(header).body(commentList);
	    } catch (Exception e) {
	        return exceptionHandling(e);
	    }
	}


	@GetMapping("/{email}")
	public ResponseEntity<?> userComment(
	    @PathVariable("email") @Parameter(description = "공지사항을 얻기 위한 contentId.", required = true) String email) {
	    log.info("getListComment contentId - {}", email);
	    try {
	        List<CommentDto> commentList = commentService.userComment(email);
	        for(CommentDto comm : commentList) {
	        	log.info(comm.toString());
	        }
	        HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	        return ResponseEntity.ok().headers(header).body(commentList);
	    } catch (Exception e) {
	        return exceptionHandling(e);
	    }
	}
//	@Operation(summary = "댓글 게시판 글작성", description = "")
//	@GetMapping("/{noticesNo}")
//	public ResponseEntity<NoticesDto> getArticle(
//			@PathVariable("noticesNo") @Parameter(name = "noticesNo", description = "얻어올 글의 글번호.", required = true) int noticesNo)
//			throws Exception {
//		log.info("getArticle - 호출 : " + noticesNo);
//		//noticesService.updateHit(noticesNo);
//		return new ResponseEntity<NoticesDto>(noticesService.getArticle(noticesNo), HttpStatus.OK);
//	}

	

	@Operation(summary = "댓글 내용 수정", description = "수정할 공지사항 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
	@PutMapping
	public ResponseEntity<String> modifyComment(
			@RequestBody @Parameter(description = "수정할 공지사항 정보.", required = true) CommentDto commentDto) throws Exception {
		log.info("modifyArticle - 호출 {}", commentDto);

		commentService.modifyComment(commentDto);
		return ResponseEntity.ok().build();
	}
	
	@Operation(summary = "공지사항 삭제", description = "공지사항 번호에 해당하는 공지사항의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
	@DeleteMapping("/{noticesNo}")
	public ResponseEntity<String> deleteComment(@PathVariable("noticesNo") @Parameter(name = "noticesNo", description = "살제할 글의 글번호.", required = true) Map<String,String> map) throws Exception {
		log.info("deleteArticle - 호출");
		commentService.deleteComment(map);
		return ResponseEntity.ok().build();
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}