package com.sgaraba.library.service;

import com.sgaraba.library.service.dto.DestinatairDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sgaraba.library.domain.Destinatair}.
 */
public interface DestinatairService {
    /**
     * Save a destinatair.
     *
     * @param destinatairDTO the entity to save.
     * @return the persisted entity.
     */
    DestinatairDTO save(DestinatairDTO destinatairDTO);

    /**
     * Updates a destinatair.
     *
     * @param destinatairDTO the entity to update.
     * @return the persisted entity.
     */
    DestinatairDTO update(DestinatairDTO destinatairDTO);

    /**
     * Partially updates a destinatair.
     *
     * @param destinatairDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DestinatairDTO> partialUpdate(DestinatairDTO destinatairDTO);

    /**
     * Get all the destinatairs.
     *
     * @return the list of entities.
     */
    List<DestinatairDTO> findAll();

    /**
     * Get the "id" destinatair.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DestinatairDTO> findOne(Long id);

    /**
     * Delete the "id" destinatair.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
