package com.serheev.datainfoservice.service;

import com.serheev.datainfoservice.model.MeasurementType;
import com.serheev.datainfoservice.model.Summary;
import com.serheev.datainfoservice.model.SummaryType;
import com.serheev.datainfoservice.model.exception.SensorNotFoundException;
import com.serheev.datainfoservice.repository.SummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;

    @Override
    public Summary get(
            Long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    ) {
        return summaryRepository.findBySensorId(
                        sensorId,
                        measurementTypes == null ? Set.of(MeasurementType.values()) : measurementTypes,
                        summaryTypes == null ? Set.of(SummaryType.values()) : summaryTypes
                )
                .orElseThrow(SensorNotFoundException::new);
    }

}
