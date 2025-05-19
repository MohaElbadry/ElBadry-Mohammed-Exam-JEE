package com.elbadry.mohammed.mappers;

import com.elbadry.mohammed.dtos.CreditDTO;
import com.elbadry.mohammed.dtos.CreditImmobilierDTO;
import com.elbadry.mohammed.dtos.CreditPersonnelDTO;
import com.elbadry.mohammed.dtos.CreditProfessionnelDTO;
import com.elbadry.mohammed.entities.Credit;
import com.elbadry.mohammed.entities.CreditImmobilier;
import com.elbadry.mohammed.entities.CreditPersonnel;
import com.elbadry.mohammed.entities.CreditProfessionnel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RemboursementMapper.class})
public interface CreditMapper {
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "type", source = ".", qualifiedByName = "creditType")
    CreditDTO toDto(Credit credit);
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "type", constant = "PERSONNEL")
    CreditPersonnelDTO toPersonnelDto(CreditPersonnel creditPersonnel);
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "remboursements", ignore = true)
    CreditPersonnel toPersonnelEntity(CreditPersonnelDTO creditPersonnelDTO);
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "type", constant = "IMMOBILIER")
    CreditImmobilierDTO toImmobilierDto(CreditImmobilier creditImmobilier);
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "remboursements", ignore = true)
    CreditImmobilier toImmobilierEntity(CreditImmobilierDTO creditImmobilierDTO);
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "type", constant = "PROFESSIONNEL")
    CreditProfessionnelDTO toProfessionnelDto(CreditProfessionnel creditProfessionnel);
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "remboursements", ignore = true)
    CreditProfessionnel toProfessionnelEntity(CreditProfessionnelDTO creditProfessionnelDTO);
    List<CreditDTO> toDtoList(List<Credit> credits);
    @Named("creditType")
    default String getCreditType(Credit credit) {
        if (credit instanceof CreditPersonnel) {
            return "PERSONNEL";
        } else if (credit instanceof CreditImmobilier) {
            return "IMMOBILIER";
        } else if (credit instanceof CreditProfessionnel) {
            return "PROFESSIONNEL";
        }
        return "UNKNOWN";
    }
}
