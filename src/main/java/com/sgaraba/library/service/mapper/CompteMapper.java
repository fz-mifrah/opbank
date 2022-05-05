package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Banque;
import com.sgaraba.library.domain.CarteBancaire;
import com.sgaraba.library.domain.Compte;
import com.sgaraba.library.service.dto.BanqueDTO;
import com.sgaraba.library.service.dto.CarteBancaireDTO;
import com.sgaraba.library.service.dto.CompteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Compte} and its DTO {@link CompteDTO}.
 */
@Mapper(componentModel = "spring")
public interface CompteMapper extends EntityMapper<CompteDTO, Compte> {
    @Mapping(target = "rib", source = "rib", qualifiedByName = "carteBancaireId")
    @Mapping(target = "dateOuverture", source = "dateOuverture", qualifiedByName = "banqueId")
    CompteDTO toDto(Compte s);

    @Named("carteBancaireId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CarteBancaireDTO toDtoCarteBancaireId(CarteBancaire carteBancaire);

    @Named("banqueId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BanqueDTO toDtoBanqueId(Banque banque);
}
