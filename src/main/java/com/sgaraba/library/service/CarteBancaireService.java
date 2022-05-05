package com.sgaraba.library.service;

import com.sgaraba.library.domain.CarteBancaire;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CarteBancaire}.
 */
public interface CarteBancaireService {
    /**
     * Save a carteBancaire.
     *
     * @param carteBancaire the entity to save.
     * @return the persisted entity.
     */
    CarteBancaire save(CarteBancaire carteBancaire);

    /**
     * Updates a carteBancaire.
     *
     * @param carteBancaire the entity to update.
     * @return the persisted entity.
     */
    CarteBancaire update(CarteBancaire carteBancaire);

    /**
     * Partially updates a carteBancaire.
     *
     * @param carteBancaire the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CarteBancaire> partialUpdate(CarteBancaire carteBancaire);

    /**
     * Get all the carteBancaires.
     *
     * @return the list of entities.
     */
    List<CarteBancaire> findAll();
    /**
     * Get all the CarteBancaire where Compte is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<CarteBancaire> findAllWhereCompteIsNull();

    /**
     * Get the "id" carteBancaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CarteBancaire> findOne(Long id);

    /**
     * Delete the "id" carteBancaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
