package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.dto.AdminDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdminMapper {
    Admin toEntity(AdminDTO adminDTO);

    AdminDTO toDto(Admin admin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Admin partialUpdate(AdminDTO adminDTO, @MappingTarget Admin admin);
}