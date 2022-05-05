package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Transfer;
import com.sgaraba.library.service.dto.TransferDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transfer} and its DTO {@link TransferDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransferMapper extends EntityMapper<TransferDTO, Transfer> {}
