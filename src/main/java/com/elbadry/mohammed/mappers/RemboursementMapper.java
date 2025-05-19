package com.elbadry.mohammed.mappers;

import com.elbadry.mohammed.dtos.RemboursementDTO;
import com.elbadry.mohammed.entities.Remboursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RemboursementMapper {
    @Mapping(target = "creditId", source = "credit.id")
    RemboursementDTO toDto(Remboursement remboursement);
    
    @Mapping(target = "credit", ignore = true)
    Remboursement toEntity(RemboursementDTO remboursementDTO);
    
    List<RemboursementDTO> toDtoList(List<Remboursement> remboursements);
}
