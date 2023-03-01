package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.dto.ProgramDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {ExerciseMapper.class})
public interface ProgramMapper {
    @Mapping(source = "scheduleId", target = "schedule.id")
    Program toEntity(ProgramDTO programDTO);

    @Mapping(source = "schedule.id", target = "scheduleId")
    ProgramDTO toDto(Program program);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "scheduleId", target = "schedule.id")
    Program partialUpdate(ProgramDTO programDTO, @MappingTarget Program program);
}