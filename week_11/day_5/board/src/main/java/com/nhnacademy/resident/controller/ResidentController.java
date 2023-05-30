package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.resident.ResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentPageDto;
import com.nhnacademy.resident.service.resident.ResidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/residents")
public class ResidentController {
    ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public ModelAndView getResidents(Pageable pageable) {
        ModelAndView mav = new ModelAndView();

        Page<ResidentDto> residentsByPage = residentService.getResidentsByPage(pageable);
        mav.setViewName("home");
        mav.addObject("residents", residentsByPage);
        return mav;
    }

    @GetMapping("/{serialNumber}")
    public ModelAndView getResidentReports(@PathVariable Long serialNumber) {
        ModelAndView mav = new ModelAndView();
        ResidentPageDto residentPageDto = residentService.getResidentReport(serialNumber);
        mav.setViewName("resident-page");
        mav.addObject("residentPageDto", residentPageDto);
        return mav;
    }

    @GetMapping("/{serialNumber}/delete")
    public String deleteResident(@PathVariable Long serialNumber) {
        residentService.deleteResident(serialNumber);
        return "redirect:/residents?page=0size=20";
    }
}
