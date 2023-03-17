package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserProfileDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {
    User toEntity(UserProfileDTO userProfileDTO);

    UserProfileDTO toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserProfileDTO userProfileDTO, @MappingTarget User user);
}