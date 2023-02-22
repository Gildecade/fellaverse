package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseBuyDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourseBuyMapper {
    Course toEntity(CourseBuyDTO courseBuyDTO);

    CourseBuyDTO toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course partialUpdate(CourseBuyDTO courseBuyDTO, @MappingTarget Course course);
}