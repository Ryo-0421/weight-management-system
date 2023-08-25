package com.example.weight_management_system.service;

import com.example.weight_management_system.model.MWeight;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WeightService {

    void recordWeight(MWeight weight);

    List<MWeight> findWeight(int userId);

    void updateOneWeight(int userId, LocalDateTime createdAt, BigDecimal weight, LocalDate recordedDate);
}
