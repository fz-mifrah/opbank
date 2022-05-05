package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Facture;
import com.sgaraba.library.domain.PaimentFacture;
import com.sgaraba.library.service.dto.FactureDTO;
import com.sgaraba.library.service.dto.PaimentFactureDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaimentFacture} and its DTO {@link PaimentFactureDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaimentFactureMapper extends EntityMapper<PaimentFactureDTO, PaimentFacture> {
    @Mapping(target = "factures", source = "factures", qualifiedByName = "factureNomSet")
    PaimentFactureDTO toDto(PaimentFacture s);

    @Mapping(target = "removeFacture", ignore = true)
    PaimentFacture toEntity(PaimentFactureDTO paimentFactureDTO);

    @Named("factureNom")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nom", source = "nom")
    FactureDTO toDtoFactureNom(Facture facture);

    @Named("factureNomSet")
    default Set<FactureDTO> toDtoFactureNomSet(Set<Facture> facture) {
        return facture.stream().map(this::toDtoFactureNom).collect(Collectors.toSet());
    }
}
