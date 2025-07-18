package com.example.weight_management_system.repository;

import com.example.weight_management_system.model.MWeight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface WeightMapper {

    void insertOneWeight(MWeight weight);

    List<MWeight> getWeight(int userId);

    void updateWeight(@Param("userId") int userId, @Param("createdAt") LocalDateTime createdAt,
                      @Param("weight") BigDecimal weight, @Param("recordedDate") LocalDate recordedDate);

    List<MWeight> findWeightForPagination(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    long findWeightForPaginationCount(int userId);

    void updateSelectedWeight(@Param("createdAt") String createdAt, @Param("weight") BigDecimal weight,
                              @Param("recordedDate") LocalDate recordedDate);

    MWeight getWeightByCreatedAt(LocalDateTime createdAt);

    void deleteWeight(@Param("createdAt") String createdAt);
}
