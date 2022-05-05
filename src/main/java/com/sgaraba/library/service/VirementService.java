package com.sgaraba.library.service;

import com.sgaraba.library.domain.Virement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Virement}.
 */
public interface VirementService {
    /**
     * Save a virement.
     *
     * @param virement the entity to save.
     * @return the persisted entity.
     */
    Virement save(Virement virement);

    /**
     * Updates a virement.
     *
     * @param virement the entity to update.
     * @return the persisted entity.
     */
    Virement update(Virement virement);

    /**
     * Partially updates a virement.
     *
     * @param virement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Virement> partialUpdate(Virement virement);

    /**
     * Get all the virements.
     *
     * @return the list of entities.
     */
    List<Virement> findAll();
    /**
     * Get all the Virement where Operation is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Virement> findAllWhereOperationIsNull();

    /**
     * Get the "id" virement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Virement> findOne(Long id);

    /**
     * Delete the "id" virement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
