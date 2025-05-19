package com.elbadry.mohammed.mappers;

import com.elbadry.mohammed.dtos.UtilisateurDTO;
import com.elbadry.mohammed.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    @Mapping(target = "clientId", source = "client.id")
    UtilisateurDTO toDto(Utilisateur utilisateur);
    
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "client", ignore = true)
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);
    
    List<UtilisateurDTO> toDtoList(List<Utilisateur> utilisateurs);
}
