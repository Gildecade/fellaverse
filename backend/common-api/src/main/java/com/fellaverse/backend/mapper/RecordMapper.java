package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Record;
import com.fellaverse.backend.dto.RecordDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {RecordIdMapper.class, UserMapper.class, ExerciseMapper.class})
public interface RecordMapper {
    Record toEntity(RecordDTO recordDTO);

    RecordDTO toDto(Record record);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Record partialUpdate(RecordDTO recordDTO, @MappingTarget Record record);
}