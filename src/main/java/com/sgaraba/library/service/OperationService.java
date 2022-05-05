package com.sgaraba.library.service;

import com.sgaraba.library.domain.Operation;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Operation}.
 */
public interface OperationService {
    /**
     * Save a operation.
     *
     * @param operation the entity to save.
     * @return the persisted entity.
     */
    Operation save(Operation operation);

    /**
     * Updates a operation.
     *
     * @param operation the entity to update.
     * @return the persisted entity.
     */
    Operation update(Operation operation);

    /**
     * Partially updates a operation.
     *
     * @param operation the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Operation> partialUpdate(Operation operation);

    /**
     * Get all the operations.
     *
     * @return the list of entities.
     */
    List<Operation> findAll();

    /**
     * Get the "id" operation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Operation> findOne(Long id);

    /**
     * Delete the "id" operation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
