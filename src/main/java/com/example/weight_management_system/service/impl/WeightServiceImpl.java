package com.example.weight_management_system.service.impl;

import com.example.weight_management_system.model.MWeight;
import com.example.weight_management_system.repository.WeightMapper;
import com.example.weight_management_system.service.WeightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class WeightServiceImpl implements WeightService {

    private final WeightMapper weightMapper;
    private final HttpSession session;

    public WeightServiceImpl(WeightMapper weightMapper, HttpSession session) {
        this.weightMapper = weightMapper;
        this.session = session;
    }

    @Override
    public void recordWeight(MWeight weight) {
        int userId = (int) session.getAttribute("userId");
        if (weight == null) throw new NullPointerException("weightは値を持ちません。");

        List<MWeight> weights = this.findWeight(userId);
        boolean isRecordedDateAlreadyExists = false;

        for (MWeight existingWeight : weights) {
            if (Objects.equals(existingWeight.getRecordedDate(), weight.getRecordedDate())) {
                isRecordedDateAlreadyExists = true;
                break;
            }
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        weight.setCreatedAt(currentDateTime);

        if (!isRecordedDateAlreadyExists) {
            weight.setUserId(userId);
            this.weightMapper.insertOneWeight(weight);
        } else {
            this.updateOneWeight(userId, weight.getCreatedAt(), weight.getWeight(), weight.getRecordedDate());
        }
    }

    @Override
    public List<MWeight> findWeight(int userId) {
        return this.weightMapper.getWeight(userId);
    }

    @Override
    public void updateOneWeight(int userId, LocalDateTime createdAt, BigDecimal weight, LocalDate recordedDate) {
        this.weightMapper.updateWeight(userId, createdAt, weight, recordedDate);
    }

    @Override
    public Page<MWeight> getWeights(int userId, Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        List<MWeight> content = this.weightMapper.findWeightForPagination(userId, offset, pageable.getPageSize());
        long total = this.weightMapper.findWeightForPaginationCount(userId);
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public void editWeight(String createdAt, BigDecimal weight, LocalDate recordedDate) {
        this.weightMapper.updateSelectedWeight(createdAt, weight, recordedDate);
    }

    @Override
    public MWeight findWeightByCreatedAt(LocalDateTime createdAt) {
         return this.weightMapper.getWeightByCreatedAt(createdAt);
    }

    @Override
    public void deleteOneWeight(String createdAt) {
        this.weightMapper.deleteWeight(createdAt);
    }
}
