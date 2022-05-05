package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Facture;
import com.sgaraba.library.service.dto.FactureDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Facture} and its DTO {@link FactureDTO}.
 */
@Mapper(componentModel = "spring")
public interface FactureMapper extends EntityMapper<FactureDTO, Facture> {}
