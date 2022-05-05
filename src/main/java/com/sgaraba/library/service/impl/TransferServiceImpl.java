package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Transfer;
import com.sgaraba.library.repository.TransferRepository;
import com.sgaraba.library.service.TransferService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Transfer}.
 */
@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    private final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Transfer save(Transfer transfer) {
        log.debug("Request to save Transfer : {}", transfer);
        return transferRepository.save(transfer);
    }

    @Override
    public Transfer update(Transfer transfer) {
        log.debug("Request to save Transfer : {}", transfer);
        return transferRepository.save(transfer);
    }

    @Override
    public Optional<Transfer> partialUpdate(Transfer transfer) {
        log.debug("Request to partially update Transfer : {}", transfer);

        return transferRepository
            .findById(transfer.getId())
            .map(existingTransfer -> {
                if (transfer.getCinDestinataireII() != null) {
                    existingTransfer.setCinDestinataireII(transfer.getCinDestinataireII());
                }
                if (transfer.getNomPrenomDestinataireII() != null) {
                    existingTransfer.setNomPrenomDestinataireII(transfer.getNomPrenomDestinataireII());
                }
                if (transfer.getTelDestinataireII() != null) {
                    existingTransfer.setTelDestinataireII(transfer.getTelDestinataireII());
                }

                return existingTransfer;
            })
            .map(transferRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Transfer> findAll(Pageable pageable) {
        log.debug("Request to get all Transfers");
        return transferRepository.findAll(pageable);
    }

    /**
     *  Get all the transfers where Operation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Transfer> findAllWhereOperationIsNull() {
        log.debug("Request to get all transfers where Operation is null");
        return StreamSupport
            .stream(transferRepository.findAll().spliterator(), false)
            .filter(transfer -> transfer.getOperation() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transfer> findOne(Long id) {
        log.debug("Request to get Transfer : {}", id);
        return transferRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transfer : {}", id);
        transferRepository.deleteById(id);
    }
}
