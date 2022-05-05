package com.sgaraba.library.service;

import com.sgaraba.library.domain.Transfer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Transfer}.
 */
public interface TransferService {
    /**
     * Save a transfer.
     *
     * @param transfer the entity to save.
     * @return the persisted entity.
     */
    Transfer save(Transfer transfer);

    /**
     * Updates a transfer.
     *
     * @param transfer the entity to update.
     * @return the persisted entity.
     */
    Transfer update(Transfer transfer);

    /**
     * Partially updates a transfer.
     *
     * @param transfer the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Transfer> partialUpdate(Transfer transfer);

    /**
     * Get all the transfers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Transfer> findAll(Pageable pageable);
    /**
     * Get all the Transfer where Operation is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Transfer> findAllWhereOperationIsNull();

    /**
     * Get the "id" transfer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Transfer> findOne(Long id);

    /**
     * Delete the "id" transfer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
