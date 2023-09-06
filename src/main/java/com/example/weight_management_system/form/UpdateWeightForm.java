package com.example.weight_management_system.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateWeightForm {
    private Integer userId;
    private String createdAt;
    @NotNull
    @DecimalMin(value = "1.0")
    @DecimalMax(value = "999.9")
    private BigDecimal weight;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordedDate;
}
