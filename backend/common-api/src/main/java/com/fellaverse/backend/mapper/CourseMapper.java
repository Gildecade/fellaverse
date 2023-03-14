package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseDTO;
import com.fellaverse.backend.dto.CourseRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDTO courseDTO);

    CourseDTO toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course partialUpdate(CourseDTO courseDTO, @MappingTarget Course course);

    Course toEntity1(CourseRecord courseRecord);

    CourseRecord toDto1(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course partialUpdate1(CourseRecord courseRecord, @MappingTarget Course course);
}