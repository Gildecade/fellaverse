package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.AdminRole;
import com.fellaverse.backend.dto.AdminRoleDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AdminMapper.class, RoleMapper.class})
public interface AdminRoleMapper {
    AdminRole toEntity(AdminRoleDTO adminRoleDTO);

    AdminRoleDTO toDto(AdminRole adminRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdminRole partialUpdate(AdminRoleDTO adminRoleDTO, @MappingTarget AdminRole adminRole);
}