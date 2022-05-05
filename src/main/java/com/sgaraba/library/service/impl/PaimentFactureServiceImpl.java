package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.PaimentFacture;
import com.sgaraba.library.repository.PaimentFactureRepository;
import com.sgaraba.library.service.PaimentFactureService;
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
 * Service Implementation for managing {@link PaimentFacture}.
 */
@Service
@Transactional
public class PaimentFactureServiceImpl implements PaimentFactureService {

    private final Logger log = LoggerFactory.getLogger(PaimentFactureServiceImpl.class);

    private final PaimentFactureRepository paimentFactureRepository;

    public PaimentFactureServiceImpl(PaimentFactureRepository paimentFactureRepository) {
        this.paimentFactureRepository = paimentFactureRepository;
    }

    @Override
    public PaimentFacture save(PaimentFacture paimentFacture) {
        log.debug("Request to save PaimentFacture : {}", paimentFacture);
        return paimentFactureRepository.save(paimentFacture);
    }

    @Override
    public PaimentFacture update(PaimentFacture paimentFacture) {
        log.debug("Request to save PaimentFacture : {}", paimentFacture);
        return paimentFactureRepository.save(paimentFacture);
    }

    @Override
    public Optional<PaimentFacture> partialUpdate(PaimentFacture paimentFacture) {
        log.debug("Request to partially update PaimentFacture : {}", paimentFacture);

        return paimentFactureRepository
            .findById(paimentFacture.getId())
            .map(existingPaimentFacture -> {
                if (paimentFacture.getReferance() != null) {
                    existingPaimentFacture.setReferance(paimentFacture.getReferance());
                }

                return existingPaimentFacture;
            })
            .map(paimentFactureRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PaimentFacture> findAll(Pageable pageable) {
        log.debug("Request to get all PaimentFactures");
        return paimentFactureRepository.findAll(pageable);
    }

    public Page<PaimentFacture> findAllWithEagerRelationships(Pageable pageable) {
        return paimentFactureRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     *  Get all the paimentFactures where Operation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PaimentFacture> findAllWhereOperationIsNull() {
        log.debug("Request to get all paimentFactures where Operation is null");
        return StreamSupport
            .stream(paimentFactureRepository.findAll().spliterator(), false)
            .filter(paimentFacture -> paimentFacture.getOperation() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaimentFacture> findOne(Long id) {
        log.debug("Request to get PaimentFacture : {}", id);
        return paimentFactureRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaimentFacture : {}", id);
        paimentFactureRepository.deleteById(id);
    }
}
