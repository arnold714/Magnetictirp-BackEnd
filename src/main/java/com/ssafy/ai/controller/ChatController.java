package com.ssafy.ai.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class ChatController {
    private final OpenAiChatModel openAiChatModel;

    public ChatController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        // 요청 데이터 검증
     

        String userMessage = request.get("message");

        // 프롬프트 정의
        String prompt = "당신은 여행 리뷰들을 요약하는 사람입니다. 여행지의 리뷰들을 보고 여행지를 종합적으로 판단하여 사용자에게 알려줘야할 의무가 있습니다. 리뷰들을 분석하고 장점들에 대해서 먼저 설명하되 '장점 :' 이런식으로 "
        		+ "설명하는 것이 아닌 장점에 대해 풀어쓰고 단점 역시 풀어서 설명한 다음 종합적인 평가를 내려주세요. 응답은 공백 포함 총 250자로 응답해";
        userMessage = userMessage.replaceAll("[\\r\\n]+", " ").trim();
        // OpenAI API에 보낼 전체 메시지 구성
        String combinedMessage = prompt + "\n\n" + userMessage;

        // OpenAI 모델 호출
        String openAiResponse = openAiChatModel.call(combinedMessage);

        // 응답 구성
        Map<String, String> responses = new HashMap<>();
        responses.put("openai(chatGPT) 응답", openAiResponse);

        return responses;
    }

    // 예외 처리 핸들러
   
}
