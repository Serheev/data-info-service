package com.serheev.datainfoservice.repository;

import com.serheev.datainfoservice.model.MeasurementType;
import com.serheev.datainfoservice.model.Summary;
import com.serheev.datainfoservice.model.SummaryType;

import java.util.Optional;
import java.util.Set;

public interface SummaryRepository {

    Optional<Summary> findBySensorId(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );

}
