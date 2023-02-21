package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDto courseDto);

    CourseDto toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course partialUpdate(CourseDto courseDto, @MappingTarget Course course);
}