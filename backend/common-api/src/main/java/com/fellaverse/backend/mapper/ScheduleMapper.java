package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Schedule;
import com.fellaverse.backend.dto.ScheduleDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScheduleMapper {
    Schedule toEntity(ScheduleDTO scheduleDTO);

    ScheduleDTO toDto(Schedule schedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Schedule partialUpdate(ScheduleDTO scheduleDTO, @MappingTarget Schedule schedule);
}