package com.axity.curso.retailservice.mapper;

import com.axity.curso.retailservice.dto.PurchaseOrderDTO;
import com.axity.curso.retailservice.model.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    PurchaseOrderDTO toDTO(PurchaseOrder entity);
    
    @Mapping(target = "deleted", ignore = true)
    PurchaseOrder toEntity(PurchaseOrderDTO dto);
}