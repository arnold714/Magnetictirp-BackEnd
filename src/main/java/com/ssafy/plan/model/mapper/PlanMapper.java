package com.ssafy.plan.model.mapper;

import com.ssafy.plan.model.PlanCreateDto;
import com.ssafy.plan.model.PlanResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface    PlanMapper {
    List<PlanResponseDto> listPlan(Map<String, Object> param) throws SQLException;

    int getTotalAttractionCount() throws SQLException;

    String getSidoName(@Param("sidoCode") int sidoCode) throws SQLException;
    String getGugunName(@Param("sidoCode") int sidoCode, @Param("gugunCode") int gugunCode) throws SQLException;

    void createPlan(PlanCreateDto planCreateDto) throws SQLException;
    PlanResponseDto getPlan(@Param("planId") int planId) throws SQLException;

    void deletePlan(@Param("planId") int planId) throws SQLException;
}
