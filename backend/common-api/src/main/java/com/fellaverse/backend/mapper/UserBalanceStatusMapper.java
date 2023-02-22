package com.fellaverse.backend.mapper;

import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.bean.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserBalanceStatusMapper {
    User toEntity(UserBalanceStatusDTO userBalanceStatusDTO);

    UserBalanceStatusDTO toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserBalanceStatusDTO userBalanceStatusDTO, @MappingTarget User user);
}