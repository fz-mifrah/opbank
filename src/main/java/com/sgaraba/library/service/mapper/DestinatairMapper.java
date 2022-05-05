package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Destinatair;
import com.sgaraba.library.domain.Virement;
import com.sgaraba.library.service.dto.DestinatairDTO;
import com.sgaraba.library.service.dto.VirementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Destinatair} and its DTO {@link DestinatairDTO}.
 */
@Mapper(componentModel = "spring")
public interface DestinatairMapper extends EntityMapper<DestinatairDTO, Destinatair> {
    @Mapping(target = "rib", source = "rib", qualifiedByName = "virementId")
    DestinatairDTO toDto(Destinatair s);

    @Named("virementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    VirementDTO toDtoVirementId(Virement virement);
}
