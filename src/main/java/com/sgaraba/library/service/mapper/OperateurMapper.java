package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Operateur;
import com.sgaraba.library.service.dto.OperateurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Operateur} and its DTO {@link OperateurDTO}.
 */
@Mapper(componentModel = "spring")
public interface OperateurMapper extends EntityMapper<OperateurDTO, Operateur> {}
