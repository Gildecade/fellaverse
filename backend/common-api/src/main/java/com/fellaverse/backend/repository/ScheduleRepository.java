package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Schedule;
import com.fellaverse.backend.projection.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<ScheduleInfo> findByUserId(Long id);

}