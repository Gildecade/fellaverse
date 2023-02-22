package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.dto.ExerciseDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ExerciseMapper {
    Exercise toEntity(ExerciseDTO exerciseDTO);

    ExerciseDTO toDto(Exercise exercise);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Exercise partialUpdate(ExerciseDTO exerciseDTO, @MappingTarget Exercise exercise);
}