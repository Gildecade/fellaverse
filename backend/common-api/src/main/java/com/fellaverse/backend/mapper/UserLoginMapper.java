package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserLoginDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserLoginMapper {
    User toEntity(UserLoginDTO userLoginDTO);

    UserLoginDTO toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserLoginDTO userLoginDTO, @MappingTarget User user);
}