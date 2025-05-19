package com.elbadry.mohammed.mappers;

import com.elbadry.mohammed.dtos.ClientDTO;
import com.elbadry.mohammed.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CreditMapper.class})
public interface ClientMapper {
    @Mapping(target = "credits", source = "credits")
    ClientDTO toDto(Client client);
    
    @Mapping(target = "credits", ignore = true)
    @Mapping(target = "utilisateur", ignore = true)
    Client toEntity(ClientDTO clientDTO);
    
    List<ClientDTO> toDtoList(List<Client> clients);
}
