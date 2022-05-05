package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Virement;
import com.sgaraba.library.service.dto.VirementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Virement} and its DTO {@link VirementDTO}.
 */
@Mapper(componentModel = "spring")
public interface VirementMapper extends EntityMapper<VirementDTO, Virement> {}
