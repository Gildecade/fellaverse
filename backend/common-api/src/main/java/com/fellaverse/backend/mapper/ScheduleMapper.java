package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Schedule;
import com.fellaverse.backend.dto.ScheduleDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(source = "userId", target = "user.id")
    Schedule toEntity(ScheduleDTO scheduleDTO);

    @Mapping(source = "user.id", target = "userId")
    ScheduleDTO toDto(Schedule schedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "userId", target = "user.id")
    Schedule partialUpdate(ScheduleDTO scheduleDTO, @MappingTarget Schedule schedule);
}