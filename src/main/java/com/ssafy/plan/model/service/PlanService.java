package com.ssafy.plan.model.service;

import com.ssafy.plan.model.PlanCreateDto;
import com.ssafy.plan.model.PlanListDto;
import com.ssafy.plan.model.PlanRequestDto;
import com.ssafy.plan.model.PlanResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface PlanService{
    PlanListDto listPlan(Map<String, String> map) throws Exception;
    int createPlan(PlanCreateDto planCreateDto) throws Exception;
    PlanRequestDto getPlan(int planId) throws Exception;
}
