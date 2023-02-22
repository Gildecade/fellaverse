package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.dto.ProgramDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProgramMapper {
    Program toEntity(ProgramDTO programDTO);

    ProgramDTO toDto(Program program);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Program partialUpdate(ProgramDTO programDTO, @MappingTarget Program program);
}