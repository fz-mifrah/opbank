package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Virement;
import com.sgaraba.library.repository.VirementRepository;
import com.sgaraba.library.service.VirementService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Virement}.
 */
@Service
@Transactional
public class VirementServiceImpl implements VirementService {

    private final Logger log = LoggerFactory.getLogger(VirementServiceImpl.class);

    private final VirementRepository virementRepository;

    public VirementServiceImpl(VirementRepository virementRepository) {
        this.virementRepository = virementRepository;
    }

    @Override
    public Virement save(Virement virement) {
        log.debug("Request to save Virement : {}", virement);
        return virementRepository.save(virement);
    }

    @Override
    public Virement update(Virement virement) {
        log.debug("Request to save Virement : {}", virement);
        return virementRepository.save(virement);
    }

    @Override
    public Optional<Virement> partialUpdate(Virement virement) {
        log.debug("Request to partially update Virement : {}", virement);

        return virementRepository
            .findById(virement.getId())
            .map(existingVirement -> {
                if (virement.getDescription() != null) {
                    existingVirement.setDescription(virement.getDescription());
                }

                return existingVirement;
            })
            .map(virementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Virement> findAll() {
        log.debug("Request to get all Virements");
        return virementRepository.findAll();
    }

    /**
     *  Get all the virements where Operation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Virement> findAllWhereOperationIsNull() {
        log.debug("Request to get all virements where Operation is null");
        return StreamSupport
            .stream(virementRepository.findAll().spliterator(), false)
            .filter(virement -> virement.getOperation() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Virement> findOne(Long id) {
        log.debug("Request to get Virement : {}", id);
        return virementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Virement : {}", id);
        virementRepository.deleteById(id);
    }
}
