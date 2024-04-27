package com.ls.service;

import com.ls.mapper.ScheduleMapper;
import com.ls.pojo.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findAll();
}
