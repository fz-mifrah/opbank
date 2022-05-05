package com.sgaraba.library.service;

import com.sgaraba.library.domain.Operateur;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Operateur}.
 */
public interface OperateurService {
    /**
     * Save a operateur.
     *
     * @param operateur the entity to save.
     * @return the persisted entity.
     */
    Operateur save(Operateur operateur);

    /**
     * Updates a operateur.
     *
     * @param operateur the entity to update.
     * @return the persisted entity.
     */
    Operateur update(Operateur operateur);

    /**
     * Partially updates a operateur.
     *
     * @param operateur the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Operateur> partialUpdate(Operateur operateur);

    /**
     * Get all the operateurs.
     *
     * @return the list of entities.
     */
    List<Operateur> findAll();

    /**
     * Get the "id" operateur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Operateur> findOne(Long id);

    /**
     * Delete the "id" operateur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
