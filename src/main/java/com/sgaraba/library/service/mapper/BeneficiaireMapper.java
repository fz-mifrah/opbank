package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Beneficiaire;
import com.sgaraba.library.domain.Virement;
import com.sgaraba.library.service.dto.BeneficiaireDTO;
import com.sgaraba.library.service.dto.VirementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Beneficiaire} and its DTO {@link BeneficiaireDTO}.
 */
@Mapper(componentModel = "spring")
public interface BeneficiaireMapper extends EntityMapper<BeneficiaireDTO, Beneficiaire> {
    @Mapping(target = "numCompte", source = "numCompte", qualifiedByName = "virementId")
    BeneficiaireDTO toDto(Beneficiaire s);

    @Named("virementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    VirementDTO toDtoVirementId(Virement virement);
}
