package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Role;
import com.fellaverse.backend.dto.RoleDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleDTO roleDTO);

    RoleDTO toDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleDTO roleDTO, @MappingTarget Role role);
}