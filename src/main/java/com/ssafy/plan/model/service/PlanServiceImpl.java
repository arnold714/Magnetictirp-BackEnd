package com.ssafy.plan.model.service;

import com.ssafy.plan.model.*;
import com.ssafy.plan.model.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanMapper planMapper;

    @Override
    public PlanRequestDto getPlan(int planId) throws Exception {
        PlanResponseDto plan = planMapper.getPlan(planId);
        if (plan == null) {
            return null;
        }

        PlanRequestDto dto = new PlanRequestDto();
        dto.setPlanId(plan.getPlanId());
        dto.setTitle(plan.getTitle());
        dto.setIsPublic(plan.getIsPublic());

        // Convert tripDay to string
        if (plan.getTripDay() > 0 && plan.getTripDay() <= 3) {
            String tripDayFormatted;
            switch (plan.getTripDay()) {
                case 3:
                    tripDayFormatted = "2박 3일";
                    break;
                case 2:
                    tripDayFormatted = "1박 2일";
                    break;
                case 1:
                default:
                    tripDayFormatted = "당일치기";
                    break;
            }
            dto.setTripDay(tripDayFormatted);
        }

        dto.setAreaCode(plan.getAreaCode());
        dto.setSiGunGuCode(plan.getSiGunGuCode());

        if (plan.getCreatedAt() != null) {
            String formattedDate = plan.getCreatedAt().toLocalDate().toString().replace("-", ".");
            dto.setCreatedAt(formattedDate);
        }

        // Set sidoName and gugunName
        dto.setSidoName(planMapper.getSidoName(plan.getAreaCode()));
        dto.setGugunName(planMapper.getGugunName(plan.getAreaCode(), plan.getSiGunGuCode()));

        return dto;
    }

    @Override
    public PlanListDto listPlan(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<>();
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("size", sizePerPage);
        log.info("listPlan param - {}", param);

        List<PlanResponseDto> list = planMapper.listPlan(param);
        log.info("listPlan list - {}", list);

        List<PlanRequestDto> planRequestDtos = list.stream().map(plan -> {
            PlanRequestDto dto = new PlanRequestDto();
            dto.setPlanId(plan.getPlanId());
            dto.setTitle(plan.getTitle());
            dto.setImage(plan.getImage());
            dto.setIsPublic(plan.getIsPublic());
            // tripDay를 문자열로 변환하여 저장
            if (plan.getTripDay()>0 && plan.getTripDay()<=3) {
                String tripDayFormatted;
                switch (plan.getTripDay()) {
                    case 3:
                        tripDayFormatted = "2박 3일";
                        break;
                    case 2:
                        tripDayFormatted = "1박 2일";
                        break;
                    case 1:
                    default:
                        tripDayFormatted = "당일치기";
                        break;
                }
                dto.setTripDay(tripDayFormatted);
            }
            dto.setAreaCode(plan.getAreaCode());
            dto.setSiGunGuCode(plan.getSiGunGuCode());
            if (plan.getCreatedAt() != null) {
                String formattedDate = plan.getCreatedAt().toLocalDate().toString().replace("-", ".");
                dto.setCreatedAt(formattedDate);
            }
            try {
                dto.setSidoName(planMapper.getSidoName(plan.getAreaCode()));
                dto.setGugunName(planMapper.getGugunName(plan.getAreaCode(), plan.getSiGunGuCode()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return dto;
        }).collect(Collectors.toList());

        int totalArticleCount = planMapper.getTotalAttractionCount();
        log.info("listPlan totalArticleCount - {}", totalArticleCount);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        PlanListDto planListDto = new PlanListDto();
        planListDto.setPlans(planRequestDtos);
        planListDto.setCurrentPage(currentPage);
        planListDto.setTotalPageCount(totalPageCount);
        return planListDto;
    }

    @Override
    public int createPlan(PlanCreateDto planCreateDto) throws Exception {
        String sidoName = planMapper.getSidoName(planCreateDto.getAreaCode());
        String title = sidoName;
        if (planCreateDto.getSigunguCode() != null) {
            String gugunName = planMapper.getGugunName(planCreateDto.getAreaCode(), planCreateDto.getSigunguCode());
            title += " " + gugunName;
        }

        String durationText;
        switch (planCreateDto.getDuration()) {
            case 3:
                durationText = "2박 3일";
                break;
            case 2:
                durationText = "1박 2일";
                break;
            case 1:
            default:
                durationText = "당일치기";
                break;
        }

        title += " " + durationText + " 여행";
        planCreateDto.setTitle(title);
        planMapper.createPlan(planCreateDto);
        return planCreateDto.getPlanId();
    }
}
