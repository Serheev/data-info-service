package com.serheev.datainfoservice.web.controller;

import com.serheev.datainfoservice.model.MeasurementType;
import com.serheev.datainfoservice.model.Summary;
import com.serheev.datainfoservice.model.SummaryType;
import com.serheev.datainfoservice.service.SummaryService;
import com.serheev.datainfoservice.web.dto.SummaryDto;
import com.serheev.datainfoservice.web.mapper.SummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final SummaryService summaryService;
    private final SummaryMapper summaryMapper;

    @GetMapping("/summary/{sensorId}")
    public SummaryDto getSummary(
            @PathVariable long sensorId,
            @RequestParam(value = "mt", required = false)
            Set<MeasurementType> measurementTypes,
            @RequestParam(value = "st", required = false)
            Set<SummaryType> summaryTypes
    ) {
        Summary summary = summaryService.get(sensorId, measurementTypes, summaryTypes);
        return summaryMapper.toDto(summary);
    }

}
