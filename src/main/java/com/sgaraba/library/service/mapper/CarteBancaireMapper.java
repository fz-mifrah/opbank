package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.CarteBancaire;
import com.sgaraba.library.service.dto.CarteBancaireDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarteBancaire} and its DTO {@link CarteBancaireDTO}.
 */
@Mapper(componentModel = "spring")
public interface CarteBancaireMapper extends EntityMapper<CarteBancaireDTO, CarteBancaire> {}
