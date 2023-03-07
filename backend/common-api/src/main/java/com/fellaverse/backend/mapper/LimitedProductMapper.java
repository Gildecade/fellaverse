package com.fellaverse.backend.mapper;

import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.dto.LimitedProductDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LimitedProductMapper {
    LimitedProduct toEntity(LimitedProductDTO limitedProductDTO);

    LimitedProductDTO toDto(LimitedProduct limitedProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LimitedProduct partialUpdate(LimitedProductDTO limitedProductDTO, @MappingTarget LimitedProduct limitedProduct);
}