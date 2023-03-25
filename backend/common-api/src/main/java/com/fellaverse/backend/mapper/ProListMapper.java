package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.ProList;
import com.fellaverse.backend.dto.ProListDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProListMapper {
    ProList toEntity(ProListDTO proListDTO);

    ProListDTO toDto(ProList proList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProList partialUpdate(ProListDTO proListDTO, @MappingTarget ProList proList);
}