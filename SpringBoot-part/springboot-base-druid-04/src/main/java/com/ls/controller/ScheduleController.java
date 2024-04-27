package com.ls.controller;

import com.ls.pojo.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/all")
    public List<Schedule> find(){
        String sql = "select * from schedule;";
        List<Schedule> scheduleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Schedule.class));
        return scheduleList;
    }
}
