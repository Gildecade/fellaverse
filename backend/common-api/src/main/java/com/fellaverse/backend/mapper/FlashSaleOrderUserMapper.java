package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.FlashSaleOrder;
import com.fellaverse.backend.dto.FlashSaleOrderUserDTO;
import com.fellaverse.backend.mapper.LimitedProductMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {LimitedProductMapper.class})
public interface FlashSaleOrderUserMapper {
    FlashSaleOrder toEntity(FlashSaleOrderUserDTO flashSaleOrderUserDTO);

    FlashSaleOrderUserDTO toDto(FlashSaleOrder flashSaleOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FlashSaleOrder partialUpdate(FlashSaleOrderUserDTO flashSaleOrderUserDTO, @MappingTarget FlashSaleOrder flashSaleOrder);
}