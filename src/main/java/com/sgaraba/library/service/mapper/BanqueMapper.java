package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Banque;
import com.sgaraba.library.service.dto.BanqueDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Banque} and its DTO {@link BanqueDTO}.
 */
@Mapper(componentModel = "spring")
public interface BanqueMapper extends EntityMapper<BanqueDTO, Banque> {}
