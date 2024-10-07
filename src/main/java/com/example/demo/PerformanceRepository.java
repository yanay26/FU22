package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query("SELECT p FROM Performance p WHERE CONCAT(p.title, ' ', p.troupe, ' ', p.date, ' ', p.time, ' ', p.totaltickets, ' ', p.availabletickets) LIKE %?1%")
    List<Performance> search(String keyword);
}
