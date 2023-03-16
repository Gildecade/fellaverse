package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.FlashSaleOrder;
import com.fellaverse.backend.dto.FlashSaleOrderDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserBasicInfoMapper.class, LimitedProductMapper.class})
public interface FlashSaleOrderMapper {
    FlashSaleOrder toEntity(FlashSaleOrderDTO flashSaleOrderDTO);

    FlashSaleOrderDTO toDto(FlashSaleOrder flashSaleOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FlashSaleOrder partialUpdate(FlashSaleOrderDTO flashSaleOrderDTO, @MappingTarget FlashSaleOrder flashSaleOrder);
}