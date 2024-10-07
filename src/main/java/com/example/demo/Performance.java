package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Performance {
    private Long id; // ID
    private String title; // Название спектакля
    private String troupe; // Название коллектива актеров
    private LocalDate date; // Дата спектакля
    private LocalTime time; // Время спектакля
    private int totaltickets; // Общее количество билетов
    private int availabletickets; // Количество свободных билетов

    protected Performance() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTroupe() {
        return troupe;
    }

    public void setTroupe(String troupe) {
        this.troupe = troupe;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getTotaltickets() {
        return totaltickets;
    }

    public void setTotaltickets(int totaltickets) {
        this.totaltickets = totaltickets;
    }

    public int getAvailabletickets() {
        return availabletickets;
    }

    public void setAvailabletickets(int availabletickets) {
        this.availabletickets = availabletickets;
    }

    @Override
    public String toString() {
        return "Performance [id=" + id + ", title=" + title + ", troupe=" + troupe + ", date=" + date + ", time=" + time + ", totaltickets=" + totaltickets + ", availabletickets=" + availabletickets + "]";
    }
}
