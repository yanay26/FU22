package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    private PerformanceService service;

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Performance> listPerformance = service.listAll(keyword);
        model.addAttribute("listPerformance", listPerformance);
        model.addAttribute("keyword", keyword);

        // Получаем количество спектаклей по дням и преобразуем в список
        Map<LocalDate, Long> performancesByDayMap = service.getPerformancesCountByDay();
        List<Map.Entry<LocalDate, Long>> performancesByDayList = new ArrayList<>(performancesByDayMap.entrySet());

        // Если у вас был код для сортировки и переворота списка, он теперь не нужен
        // Список уже отсортирован в правильном порядке в методе getPerformancesCountByDay

        model.addAttribute("performancesByDayList", performancesByDayList);

        return "index"; // Главная страница
    }



    @RequestMapping("/new")
    public String showNewPerformanceForm(Model model) {
        Performance performance = new Performance();
        model.addAttribute("performance", performance);
        return "new_performance"; // Форма для добавления спектакля
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePerformance(@ModelAttribute("performance") Performance performance) {
        service.save(performance);
        return "redirect:/"; // Перенаправление на главную страницу после сохранения
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPerformanceForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_performance"); // Страница редактирования
        Performance performance = service.get(id);
        mav.addObject("performance", performance);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deletePerformance(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/"; // Перенаправление на главную страницу после удаления
    }

    @GetMapping("/histogram/data")
    @ResponseBody
    public ResponseEntity<Map<LocalDate, Long>> getPerformancesCountByDay() {
        Map<LocalDate, Long> performancesCount = service.getPerformancesCountByDay();
        return ResponseEntity.ok(performancesCount);
    }

    @GetMapping("/histogram")
    public String showHistogram(Model model) {
        // Получаем данные для гистограммы
        Map<LocalDate, Long> performancesCount = service.getPerformancesCountByDay();
        model.addAttribute("performancesCount", performancesCount);
        return "histogram"; // Страница с гистограммой
    }
}

