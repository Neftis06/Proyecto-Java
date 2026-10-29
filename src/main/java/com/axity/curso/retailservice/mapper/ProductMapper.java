package com.axity.curso.retailservice.mapper;

import com.axity.curso.retailservice.dto.ProductDTO;
import com.axity.curso.retailservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    @Mapping(target = "sku", source = "sku")
    ProductDTO toDTO(Product entity);

    @Mapping(target = "sku", source = "sku")
    Product toEntity(ProductDTO dto);
}