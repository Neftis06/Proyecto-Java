package com.axity.curso.retailservice.mapper;

import com.axity.curso.retailservice.dto.ProductDTO;
import com.axity.curso.retailservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * componentModel = "spring" permite inyectar este mapper en los servicios usando @Autowired.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
    
public interface ProductMapper {

    // Convierte de Entidad a DTO
    ProductDTO toDTO(Product entity);

    // Convierte de DTO a Entidad
    Product toEntity(ProductDTO dto);
}