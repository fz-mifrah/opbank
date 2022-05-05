package com.sgaraba.library.service;

import com.sgaraba.library.domain.PaimentFacture;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link PaimentFacture}.
 */
public interface PaimentFactureService {
    /**
     * Save a paimentFacture.
     *
     * @param paimentFacture the entity to save.
     * @return the persisted entity.
     */
    PaimentFacture save(PaimentFacture paimentFacture);

    /**
     * Updates a paimentFacture.
     *
     * @param paimentFacture the entity to update.
     * @return the persisted entity.
     */
    PaimentFacture update(PaimentFacture paimentFacture);

    /**
     * Partially updates a paimentFacture.
     *
     * @param paimentFacture the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PaimentFacture> partialUpdate(PaimentFacture paimentFacture);

    /**
     * Get all the paimentFactures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PaimentFacture> findAll(Pageable pageable);
    /**
     * Get all the PaimentFacture where Operation is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<PaimentFacture> findAllWhereOperationIsNull();

    /**
     * Get all the paimentFactures with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PaimentFacture> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" paimentFacture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaimentFacture> findOne(Long id);

    /**
     * Delete the "id" paimentFacture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
