package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.dto.FunctionDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FunctionDTOMapper {
    Function toEntity(FunctionDTO functionDTO);

    FunctionDTO toDto(Function function);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Function partialUpdate(FunctionDTO functionDTO, @MappingTarget Function function);
}