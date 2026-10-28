package com.axity.curso.retailservice.mapper;

import com.axity.curso.retailservice.dto.PurchaseOrderDTO;
import com.axity.curso.retailservice.model.PurchaseOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    PurchaseOrderDTO toDTO(PurchaseOrder entity);

    PurchaseOrder toEntity(PurchaseOrderDTO dto);
}