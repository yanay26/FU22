package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceRepository repo;

    public List<Performance> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Performance performance) {
        repo.save(performance);
    }

    public Performance get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Map<LocalDate, Long> getPerformancesCountByDay() {
        List<Performance> allPerformances = repo.findAll(); // Получаем все спектакли
        Map<LocalDate, Long> countMap = new HashMap<>();

        // Получаем текущую дату
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(13);

        for (Performance performance : allPerformances) {
            LocalDate performanceDate = performance.getDate(); // Получаем дату спектакля

            // Учитываем только спектакли за следующие 14 дней (текущая неделя + следующая)
            if (performanceDate != null && (performanceDate.isAfter(today.minusDays(1)) && performanceDate.isBefore(endDate.plusDays(1)))) {
                countMap.put(performanceDate, countMap.getOrDefault(performanceDate, 0L) + 1);
            }
        }

        return countMap;
    }
}
