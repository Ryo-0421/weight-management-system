package com.example.weight_management_system.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MWeight {
    private Integer userId;
    private LocalDateTime createdAt;
    private BigDecimal weight;
    private LocalDate recordedDate;
}
