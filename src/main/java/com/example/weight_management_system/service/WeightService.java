package com.example.weight_management_system.service;

import com.example.weight_management_system.model.MWeight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WeightService {

    void recordWeight(MWeight weight);

    List<MWeight> findWeight(int userId);

    void updateOneWeight(int userId, LocalDateTime createdAt, BigDecimal weight, LocalDate recordedDate);

    Page<MWeight> getWeights(int userId, Pageable pageable);

    void editWeight(String createdAt, BigDecimal weight, LocalDate recordedDate);

    MWeight findWeightByCreatedAt(LocalDateTime createdAt);

    void deleteOneWeight(String createdAt);
}
