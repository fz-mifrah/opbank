package com.sgaraba.library.service;

import com.sgaraba.library.domain.Destinatair;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Destinatair}.
 */
public interface DestinatairService {
    /**
     * Save a destinatair.
     *
     * @param destinatair the entity to save.
     * @return the persisted entity.
     */
    Destinatair save(Destinatair destinatair);

    /**
     * Updates a destinatair.
     *
     * @param destinatair the entity to update.
     * @return the persisted entity.
     */
    Destinatair update(Destinatair destinatair);

    /**
     * Partially updates a destinatair.
     *
     * @param destinatair the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Destinatair> partialUpdate(Destinatair destinatair);

    /**
     * Get all the destinatairs.
     *
     * @return the list of entities.
     */
    List<Destinatair> findAll();

    /**
     * Get the "id" destinatair.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Destinatair> findOne(Long id);

    /**
     * Delete the "id" destinatair.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
