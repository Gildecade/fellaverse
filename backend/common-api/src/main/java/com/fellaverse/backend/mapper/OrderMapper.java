package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Order;
import com.fellaverse.backend.dto.OrderDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDTO orderDTO);

    OrderDTO toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDTO orderDTO, @MappingTarget Order order);
}