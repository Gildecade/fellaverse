package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.OrderId;
import com.fellaverse.backend.dto.OrderIdDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderIdMapper {
    OrderId toEntity(OrderIdDTO orderIdDTO);

    OrderIdDTO toDto(OrderId orderId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderId partialUpdate(OrderIdDTO orderIdDTO, @MappingTarget OrderId orderId);
}