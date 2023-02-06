package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.CheckIn;
import com.fellaverse.backend.dto.CheckInDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CheckInMapper {
    CheckIn toEntity(CheckInDTO checkIn);

    CheckInDTO toDto(CheckIn checkIn);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CheckIn partialUpdate(CheckInDTO checkInDTO, @MappingTarget CheckIn checkIn);
}