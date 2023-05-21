package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.birth_death_report_resident.BirthReportDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.DeathReportDto;
import com.nhnacademy.resident.domain.resident.ParentDto;
import com.nhnacademy.resident.service.birth_report_resident.BirthDeathReportResidentService;
import com.nhnacademy.resident.service.resident.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/residents")
public class BirthDeathController {
    BirthDeathReportResidentService birthDeathReportResidentService;
    ResidentService residentService;

    public BirthDeathController(BirthDeathReportResidentService birthDeathReportResidentService, ResidentService residentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
        this.residentService = residentService;
    }

    @GetMapping("/birth")
    public String getBirthReport(@RequestParam Long serialNumber, Model model) {
        BirthReportDto birthReport = birthDeathReportResidentService.findBirthReport(serialNumber);
        ParentDto father = residentService.getParent(serialNumber, "부");
        ParentDto mother = residentService.getParent(serialNumber, "모");
        model.addAttribute("birthReport", birthReport);
        model.addAttribute("father", father);
        model.addAttribute("mother", mother);
        return "birth-report";
    }

    @GetMapping("/death")
    public String getDeathReport(@RequestParam Long serialNumber, Model model) {
        DeathReportDto deathReport = birthDeathReportResidentService.findDeathReport(serialNumber);
        model.addAttribute("deathReport", deathReport);
        return "death-report";
    }
}
