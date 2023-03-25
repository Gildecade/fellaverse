package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.Order;
import com.fellaverse.backend.dto.OrderFindAllDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderFindAllMapper {
    Order toEntity(OrderFindAllDTO orderFindAllDTO);

    OrderFindAllDTO toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderFindAllDTO orderFindAllDTO, @MappingTarget Order order);
}