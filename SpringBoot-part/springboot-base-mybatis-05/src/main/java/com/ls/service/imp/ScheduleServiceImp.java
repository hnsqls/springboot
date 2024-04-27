package com.ls.service.imp;

import com.ls.mapper.ScheduleMapper;
import com.ls.pojo.Schedule;
import com.ls.service.ScheduleService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImp implements ScheduleService {
    @Resource
    private ScheduleMapper scheduleMapper;
    @Override
    public List<Schedule> findAll() {
        List<Schedule> scheduleList = scheduleMapper.findAll();
        return scheduleList;
    }
}
