package com.sgaraba.library.web.rest;

import com.sgaraba.library.repository.DestinatairRepository;
import com.sgaraba.library.service.DestinatairService;
import com.sgaraba.library.service.dto.DestinatairDTO;
import com.sgaraba.library.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sgaraba.library.domain.Destinatair}.
 */
@RestController
@RequestMapping("/api")
public class DestinatairResource {

    private final Logger log = LoggerFactory.getLogger(DestinatairResource.class);

    private static final String ENTITY_NAME = "destinatair";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DestinatairService destinatairService;

    private final DestinatairRepository destinatairRepository;

    public DestinatairResource(DestinatairService destinatairService, DestinatairRepository destinatairRepository) {
        this.destinatairService = destinatairService;
        this.destinatairRepository = destinatairRepository;
    }

    /**
     * {@code POST  /destinatairs} : Create a new destinatair.
     *
     * @param destinatairDTO the destinatairDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new destinatairDTO, or with status {@code 400 (Bad Request)} if the destinatair has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/destinatairs")
    public ResponseEntity<DestinatairDTO> createDestinatair(@Valid @RequestBody DestinatairDTO destinatairDTO) throws URISyntaxException {
        log.debug("REST request to save Destinatair : {}", destinatairDTO);
        if (destinatairDTO.getId() != null) {
            throw new BadRequestAlertException("A new destinatair cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DestinatairDTO result = destinatairService.save(destinatairDTO);
        return ResponseEntity
            .created(new URI("/api/destinatairs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /destinatairs/:id} : Updates an existing destinatair.
     *
     * @param id the id of the destinatairDTO to save.
     * @param destinatairDTO the destinatairDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated destinatairDTO,
     * or with status {@code 400 (Bad Request)} if the destinatairDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the destinatairDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/destinatairs/{id}")
    public ResponseEntity<DestinatairDTO> updateDestinatair(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DestinatairDTO destinatairDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Destinatair : {}, {}", id, destinatairDTO);
        if (destinatairDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, destinatairDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!destinatairRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DestinatairDTO result = destinatairService.update(destinatairDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, destinatairDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /destinatairs/:id} : Partial updates given fields of an existing destinatair, field will ignore if it is null
     *
     * @param id the id of the destinatairDTO to save.
     * @param destinatairDTO the destinatairDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated destinatairDTO,
     * or with status {@code 400 (Bad Request)} if the destinatairDTO is not valid,
     * or with status {@code 404 (Not Found)} if the destinatairDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the destinatairDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/destinatairs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DestinatairDTO> partialUpdateDestinatair(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DestinatairDTO destinatairDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Destinatair partially : {}, {}", id, destinatairDTO);
        if (destinatairDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, destinatairDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!destinatairRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DestinatairDTO> result = destinatairService.partialUpdate(destinatairDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, destinatairDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /destinatairs} : get all the destinatairs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of destinatairs in body.
     */
    @GetMapping("/destinatairs")
    public List<DestinatairDTO> getAllDestinatairs() {
        log.debug("REST request to get all Destinatairs");
        return destinatairService.findAll();
    }

    /**
     * {@code GET  /destinatairs/:id} : get the "id" destinatair.
     *
     * @param id the id of the destinatairDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the destinatairDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/destinatairs/{id}")
    public ResponseEntity<DestinatairDTO> getDestinatair(@PathVariable Long id) {
        log.debug("REST request to get Destinatair : {}", id);
        Optional<DestinatairDTO> destinatairDTO = destinatairService.findOne(id);
        return ResponseUtil.wrapOrNotFound(destinatairDTO);
    }

    /**
     * {@code DELETE  /destinatairs/:id} : delete the "id" destinatair.
     *
     * @param id the id of the destinatairDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/destinatairs/{id}")
    public ResponseEntity<Void> deleteDestinatair(@PathVariable Long id) {
        log.debug("REST request to delete Destinatair : {}", id);
        destinatairService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
