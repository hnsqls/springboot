package com.ls.controller;

import com.ls.pojo.Schedule;
import com.ls.service.ScheduleService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @GetMapping("/all")
    public List<Schedule> find(){
        List<Schedule> scheduleList = scheduleService.findAll();
        System.out.println("scheduleList = " + scheduleList);
        return scheduleList;
    }
}
