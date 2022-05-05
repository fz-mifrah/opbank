package com.sgaraba.library.service;

import com.sgaraba.library.domain.Banque;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Banque}.
 */
public interface BanqueService {
    /**
     * Save a banque.
     *
     * @param banque the entity to save.
     * @return the persisted entity.
     */
    Banque save(Banque banque);

    /**
     * Updates a banque.
     *
     * @param banque the entity to update.
     * @return the persisted entity.
     */
    Banque update(Banque banque);

    /**
     * Partially updates a banque.
     *
     * @param banque the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Banque> partialUpdate(Banque banque);

    /**
     * Get all the banques.
     *
     * @return the list of entities.
     */
    List<Banque> findAll();

    /**
     * Get the "id" banque.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Banque> findOne(Long id);

    /**
     * Delete the "id" banque.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
