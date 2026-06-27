package com.bloom.backend.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InsightsService {

    @Autowired
    private CycleLogRepository cycleLogRepository;

    public Map<String, Object> getInsights(Long userId) {
        List<CycleLog> logs = cycleLogRepository.findByUserIdOrderByStartDateDesc(userId);

        Map<String, Object> insights = new HashMap<>();

        if (logs.isEmpty()) {
            insights.put("message", "No cycle data available yet. Start logging your cycles to see insights.");
            return insights;
        }

        // Last period start date
        LocalDate lastPeriodDate = logs.get(0).getStartDate();
        insights.put("lastPeriodDate", lastPeriodDate);

        // Average cycle length
        double avgCycleLength = logs.stream()
                .filter(log -> log.getCycleLength() != null)
                .mapToInt(CycleLog::getCycleLength)
                .average()
                .orElse(28.0);
        insights.put("averageCycleLength", Math.round(avgCycleLength));

        // Predicted next period date
        LocalDate predictedNextPeriod = lastPeriodDate.plusDays((long) avgCycleLength);
        insights.put("predictedNextPeriod", predictedNextPeriod);

        // Total cycles logged
        insights.put("totalCyclesLogged", logs.size());

        // Average period length
        double avgPeriodLength = logs.stream()
                .filter(log -> log.getPeriodLength() != null)
                .mapToInt(CycleLog::getPeriodLength)
                .average()
                .orElse(5.0);
        insights.put("averagePeriodLength", Math.round(avgPeriodLength));

        return insights;
    }
}