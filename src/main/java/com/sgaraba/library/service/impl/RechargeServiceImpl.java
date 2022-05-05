package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Recharge;
import com.sgaraba.library.repository.RechargeRepository;
import com.sgaraba.library.service.RechargeService;
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
 * Service Implementation for managing {@link Recharge}.
 */
@Service
@Transactional
public class RechargeServiceImpl implements RechargeService {

    private final Logger log = LoggerFactory.getLogger(RechargeServiceImpl.class);

    private final RechargeRepository rechargeRepository;

    public RechargeServiceImpl(RechargeRepository rechargeRepository) {
        this.rechargeRepository = rechargeRepository;
    }

    @Override
    public Recharge save(Recharge recharge) {
        log.debug("Request to save Recharge : {}", recharge);
        return rechargeRepository.save(recharge);
    }

    @Override
    public Recharge update(Recharge recharge) {
        log.debug("Request to save Recharge : {}", recharge);
        return rechargeRepository.save(recharge);
    }

    @Override
    public Optional<Recharge> partialUpdate(Recharge recharge) {
        log.debug("Request to partially update Recharge : {}", recharge);

        return rechargeRepository
            .findById(recharge.getId())
            .map(existingRecharge -> {
                if (recharge.getNumTel() != null) {
                    existingRecharge.setNumTel(recharge.getNumTel());
                }

                return existingRecharge;
            })
            .map(rechargeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Recharge> findAll(Pageable pageable) {
        log.debug("Request to get all Recharges");
        return rechargeRepository.findAll(pageable);
    }

    public Page<Recharge> findAllWithEagerRelationships(Pageable pageable) {
        return rechargeRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     *  Get all the recharges where Operation is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Recharge> findAllWhereOperationIsNull() {
        log.debug("Request to get all recharges where Operation is null");
        return StreamSupport
            .stream(rechargeRepository.findAll().spliterator(), false)
            .filter(recharge -> recharge.getOperation() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Recharge> findOne(Long id) {
        log.debug("Request to get Recharge : {}", id);
        return rechargeRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recharge : {}", id);
        rechargeRepository.deleteById(id);
    }
}
