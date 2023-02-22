package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.*;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Schedule setSchedule(Schedule schedule);

    boolean deleteSchedule(Long id);

    boolean updateSchedule(Schedule schedule);
    List<Schedule> findAllSchedule(Long id);

    Optional<Schedule> findScheduleById(Long id);

}
