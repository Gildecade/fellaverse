package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.*;
import com.fellaverse.backend.projection.ScheduleInfo;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Schedule setSchedule(Schedule schedule);

    boolean deleteSchedule(Long id);

    boolean updateSchedule(Schedule schedule);
    List<ScheduleInfo> findAllSchedule(Long userId);

    Optional<Schedule> findScheduleById(Long id);

}
