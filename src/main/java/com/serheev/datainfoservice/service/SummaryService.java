package com.serheev.datainfoservice.service;

import com.serheev.datainfoservice.model.MeasurementType;
import com.serheev.datainfoservice.model.Summary;
import com.serheev.datainfoservice.model.SummaryType;

import java.util.Set;

public interface SummaryService {
    Summary get(
            Long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );
}
