package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.dto.FunctionIdNameDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FunctionMapper {
    Function toEntity(FunctionIdNameDTO functionIdNameDTO);

    FunctionIdNameDTO toDto(Function function);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Function partialUpdate(FunctionIdNameDTO functionIdNameDTO, @MappingTarget Function function);
}