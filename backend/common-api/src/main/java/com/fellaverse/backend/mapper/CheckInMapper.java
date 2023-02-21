package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.CheckIn;
import com.fellaverse.backend.dto.CheckInDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UserMapper.class})
public interface CheckInMapper {
    CheckIn toEntity(CheckInDTO checkInDto);

    CheckInDTO toDto(CheckIn checkIn);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CheckIn partialUpdate(CheckInDTO checkInDto, @MappingTarget CheckIn checkIn);
}