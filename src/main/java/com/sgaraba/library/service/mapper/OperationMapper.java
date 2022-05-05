package com.sgaraba.library.service.mapper;

import com.sgaraba.library.domain.Compte;
import com.sgaraba.library.domain.Operation;
import com.sgaraba.library.domain.PaimentFacture;
import com.sgaraba.library.domain.Recharge;
import com.sgaraba.library.domain.Transfer;
import com.sgaraba.library.domain.Virement;
import com.sgaraba.library.service.dto.CompteDTO;
import com.sgaraba.library.service.dto.OperationDTO;
import com.sgaraba.library.service.dto.PaimentFactureDTO;
import com.sgaraba.library.service.dto.RechargeDTO;
import com.sgaraba.library.service.dto.TransferDTO;
import com.sgaraba.library.service.dto.VirementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Operation} and its DTO {@link OperationDTO}.
 */
@Mapper(componentModel = "spring")
public interface OperationMapper extends EntityMapper<OperationDTO, Operation> {
    @Mapping(target = "typeOperation", source = "typeOperation", qualifiedByName = "virementId")
    @Mapping(target = "typeOperation", source = "typeOperation", qualifiedByName = "transferId")
    @Mapping(target = "typeOperation", source = "typeOperation", qualifiedByName = "rechargeId")
    @Mapping(target = "typeOperation", source = "typeOperation", qualifiedByName = "paimentFactureId")
    @Mapping(target = "typeOperation", source = "typeOperation", qualifiedByName = "compteId")
    OperationDTO toDto(Operation s);

    @Named("virementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    VirementDTO toDtoVirementId(Virement virement);

    @Named("transferId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransferDTO toDtoTransferId(Transfer transfer);

    @Named("rechargeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RechargeDTO toDtoRechargeId(Recharge recharge);

    @Named("paimentFactureId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PaimentFactureDTO toDtoPaimentFactureId(PaimentFacture paimentFacture);

    @Named("compteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CompteDTO toDtoCompteId(Compte compte);
}
