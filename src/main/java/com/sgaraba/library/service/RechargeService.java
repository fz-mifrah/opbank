package com.sgaraba.library.service;

import com.sgaraba.library.domain.Recharge;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Recharge}.
 */
public interface RechargeService {
    /**
     * Save a recharge.
     *
     * @param recharge the entity to save.
     * @return the persisted entity.
     */
    Recharge save(Recharge recharge);

    /**
     * Updates a recharge.
     *
     * @param recharge the entity to update.
     * @return the persisted entity.
     */
    Recharge update(Recharge recharge);

    /**
     * Partially updates a recharge.
     *
     * @param recharge the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Recharge> partialUpdate(Recharge recharge);

    /**
     * Get all the recharges.
     *
     * @return the list of entities.
     */
    List<Recharge> findAll();
    /**
     * Get all the Recharge where Operation is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Recharge> findAllWhereOperationIsNull();

    /**
     * Get all the recharges with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Recharge> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" recharge.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Recharge> findOne(Long id);

    /**
     * Delete the "id" recharge.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
