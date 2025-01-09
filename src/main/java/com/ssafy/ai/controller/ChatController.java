	package com.ssafy.ai.controller;
	
	import java.nio.charset.Charset;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	
	import org.springframework.ai.openai.OpenAiChatModel;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.MediaType;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;
	
	import com.ssafy.comment.controller.CommentController;
	import com.ssafy.comment.model.CommentDto;
	import com.ssafy.comment.model.service.CommentService;
	
	import lombok.extern.slf4j.Slf4j;
	
	
	@Slf4j
	@RestController
	@RequestMapping("/api/chat")
	public class ChatController {
	    private final OpenAiChatModel openAiChatModel;
	    private final CommentService commentService;
	    public ChatController(OpenAiChatModel openAiChatModel,CommentService commentService) {
	        this.openAiChatModel = openAiChatModel;
	        this.commentService = commentService;
	    }
	    @GetMapping("/{contentId}")
	    public ResponseEntity<?> chat(
	            @PathVariable("contentId") int contentId,
	            @RequestParam(value = "title", required = false) String title) {
	
	        String finalContent = null;
	        int cnt=0;
	        try {
	            List<CommentDto> commentList = commentService.getListComment(contentId);
	            StringBuilder concatenatedContent = new StringBuilder();
	            cnt = commentList.size();
	            for (CommentDto comm : commentList) {
	                concatenatedContent.append(comm.getContent());
	                concatenatedContent.append(" ");
	            }
	
	            finalContent = concatenatedContent.toString().trim();
	        } catch (Exception e) {
	            log.error("Error occurred while processing comments", e);
	        }
	        String prompt ="";
	        log.info(title);
	        String combinedMessage="";
	        
	        if(cnt <= 10) {
	            prompt = "너는 " + title + "에 대해 분석해서 알려줘야 해. "
	                   + "답변은 댓글의 표본이 적어 카카오앱과 네이버 후기 분석으로 대체하겠습니다. "
	                   + "여행지 제목을 표시하고, 각각 1., 2., 3. 형식으로 정보를 나눠서 설명해줘."+
	                   "그리고 마지막에는 '###종합 요약'이라는 타이틀로 알려줘";
	        } else {
	            prompt = "당신은 여행 리뷰들을 요약하는 사람입니다. 여행지의 리뷰들을 보고 여행지를 종합적으로 판단하여 사용자에게 알려줘야 할 의무가 있습니다. "
	                   + "여행지 제목을 표시하고, 각각 1., 2., 3. 형식으로 정보를 나눠서 설명해줘."+
	                   "그리고 마지막에는 '종합 요약'이라는 타이틀로 알려줘";
	        }
	        combinedMessage = prompt + "\n\n" + finalContent;
	
	        
	        String openAiResponse = openAiChatModel.call(combinedMessage);
	        log.info(openAiResponse);	
	        HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	        return ResponseEntity.ok().headers(header).body(openAiResponse);
	    }
	    // 예외 처리 핸들러
	   
	}
