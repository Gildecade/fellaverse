package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.CheckInId;
import com.fellaverse.backend.dto.CheckInIdDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CheckInIdMapper {
    CheckInId toEntity(CheckInIdDTO checkInIdDTO);

    CheckInIdDTO toDto(CheckInId checkInId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CheckInId partialUpdate(CheckInIdDTO checkInIdDTO, @MappingTarget CheckInId checkInId);
}