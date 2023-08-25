package com.example.weight_management_system.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RecordWeightForm {
    private Integer userId;
    private LocalDateTime createdAt;
    @NotNull
    @DecimalMin(value = "1.0")
    @DecimalMax(value = "999.9")
    private BigDecimal weight;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordedDate;
}
