package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Client;
import com.sgaraba.library.domain.Compte;
import com.sgaraba.library.service.dto.ClientDTO;
import com.sgaraba.library.service.dto.CompteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {
    @Mapping(target = "nom", source = "nom", qualifiedByName = "compteId")
    ClientDTO toDto(Client s);

    @Named("compteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CompteDTO toDtoCompteId(Compte compte);
}
