package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.RecordId;
import com.fellaverse.backend.dto.RecordIdDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RecordIdMapper {
    RecordId toEntity(RecordIdDTO recordIdDTO);

    RecordIdDTO toDto(RecordId recordId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RecordId partialUpdate(RecordIdDTO recordIdDTO, @MappingTarget RecordId recordId);
}