package com.sgaraba.library.service;

import com.sgaraba.library.service.dto.VirementDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sgaraba.library.domain.Virement}.
 */
public interface VirementService {
    /**
     * Save a virement.
     *
     * @param virementDTO the entity to save.
     * @return the persisted entity.
     */
    VirementDTO save(VirementDTO virementDTO);

    /**
     * Updates a virement.
     *
     * @param virementDTO the entity to update.
     * @return the persisted entity.
     */
    VirementDTO update(VirementDTO virementDTO);

    /**
     * Partially updates a virement.
     *
     * @param virementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VirementDTO> partialUpdate(VirementDTO virementDTO);

    /**
     * Get all the virements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VirementDTO> findAll(Pageable pageable);
    /**
     * Get all the VirementDTO where Operation is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<VirementDTO> findAllWhereOperationIsNull();

    /**
     * Get the "id" virement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VirementDTO> findOne(Long id);

    /**
     * Delete the "id" virement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
