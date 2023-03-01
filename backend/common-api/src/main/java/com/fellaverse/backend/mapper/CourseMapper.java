package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDTO courseDTO);

    CourseDTO toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course partialUpdate(CourseDTO courseDTO, @MappingTarget Course course);
}