package com.sgaraba.library.service;

import com.sgaraba.library.domain.Compte;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Compte}.
 */
public interface CompteService {
    /**
     * Save a compte.
     *
     * @param compte the entity to save.
     * @return the persisted entity.
     */
    Compte save(Compte compte);

    /**
     * Updates a compte.
     *
     * @param compte the entity to update.
     * @return the persisted entity.
     */
    Compte update(Compte compte);

    /**
     * Partially updates a compte.
     *
     * @param compte the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Compte> partialUpdate(Compte compte);

    /**
     * Get all the comptes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Compte> findAll(Pageable pageable);
    /**
     * Get all the Compte where Client is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Compte> findAllWhereClientIsNull();

    /**
     * Get the "id" compte.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Compte> findOne(Long id);

    /**
     * Delete the "id" compte.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
