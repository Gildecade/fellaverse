package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserBasicInfoDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserBasicInfoMapper {
    User toEntity(UserBasicInfoDTO userBasicInfoDTO);

    UserBasicInfoDTO toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserBasicInfoDTO userBasicInfoDTO, @MappingTarget User user);
}