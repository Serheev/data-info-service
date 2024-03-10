package com.serheev.datainfoservice.repository;

import com.serheev.datainfoservice.config.RedisSchema;
import com.serheev.datainfoservice.model.Data;
import com.serheev.datainfoservice.model.MeasurementType;
import com.serheev.datainfoservice.model.Summary;
import com.serheev.datainfoservice.model.SummaryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SummaryRepositoryImpl implements SummaryRepository {

    private final JedisPool jedisPool;

    @Override
    public Optional<Summary> findBySensorId(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    ) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.sismember(
                    RedisSchema.sensorKeys(),
                    String.valueOf(sensorId)
            )) {
                return Optional.empty();
            }
            if (measurementTypes.isEmpty() && !summaryTypes.isEmpty()) {
                return getSummary(
                        sensorId,
                        Set.of(MeasurementType.values()),
                        summaryTypes,
                        jedis
                );
            } else if (!measurementTypes.isEmpty() && summaryTypes.isEmpty()) {
                return getSummary(
                        sensorId,
                        measurementTypes,
                        Set.of(SummaryType.values()),
                        jedis
                );
            } else {
                return getSummary(
                        sensorId,
                        measurementTypes,
                        summaryTypes,
                        jedis
                );
            }
        }
    }

    @Override
    public void handle(Data data) {

    }

    private Optional<Summary> getSummary(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes,
            Jedis jedis
    ) {
        Summary summary = new Summary();
        summary.setSensorId(sensorId);
        for (MeasurementType mType : measurementTypes) {
            for (SummaryType sType : summaryTypes) {
                Summary.SummaryEntry entry = new Summary.SummaryEntry();
                entry.setType(sType);
                String value = jedis.hget(
                        RedisSchema.summaryKey(sensorId, mType),
                        sType.name().toLowerCase()
                );
                if (value != null) {
                    entry.setValue(Double.parseDouble(value));
                }
                summary.addValue(mType, entry);
            }
        }
        return Optional.of(summary);
    }

}
